package br.com.votehub.controller;

import br.com.votehub.model.vo.Votante;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.HashSet;

import br.com.votehub.model.DAOs.*;
import br.com.votehub.model.criptografia.Encriptador;

public class ControllerVotante {
	private Set<String> matriculasTemporarias = new HashSet<>();
	private VotanteDAO votanteRepository = new VotanteDAO();


	public void registrarVotante(String matricula, String nome, String senha) throws BusinessException, SQLException {
		this.validarRegistro( matricula, nome, senha);

		Votante vt = new Votante(matricula, nome, senha);
		votanteRepository.addVotante(vt);
		
	}

	public void validarRegistro(String matricula, String nome, String senha) throws BusinessException, SQLException {
		
		
		if (nome.isBlank() && matricula.isBlank() && senha.isBlank()) {
			throw new BusinessException("Preencha os campos.");
		}
		
		if (matricula.isBlank()) {
			throw new BusinessException("A matrícula deve ser preenchida.");
		}

		if (nome.isBlank()) {
			throw new BusinessException("O nome deve ser preenchido.");
		}

		if (senha.isBlank()) {
			throw new BusinessException("A senha deve ser preenchida.");
		}
		
		if (nome.length() > 200) {
			throw new BusinessException("O nome deve ter no máximo 200 caracteres");
		}
		
		if (verificarSeMatriculaExiste(matricula)) {
			throw new SQLException("Matrícula existente.");
		}
		
		if(matricula.length() > 200) {
			throw new BusinessException("A matrícula não pode exceder 200 caracteres.");
		}
		
		if(senha.length() < 8) {
			throw new BusinessException("A senha deve ter no mínimo 8 caracteres");
		}
		
		if(senha.length() > 100) {
			throw new BusinessException("A senha deve ter no máximo 100 caracteres");
		}

		
		
		matriculasTemporarias.add(matricula);

	}

	public void deletarVotante(int id_votante) throws BusinessException {
		validarExclusao(id_votante);

		votanteRepository.deleteVotante(id_votante);
	}

	public void validarExclusao(int id_votante) throws BusinessException {
		if (votanteRepository.searchVotanteById(id_votante) == null) {
			throw new BusinessException("Votante referido não encontrado!");
		}
	}

	public void atualizarVotante(int id_votante, String matricula, String nome)
			throws BusinessException, SQLException {
		validarAtualizacao(id_votante, matricula, nome);
		
		votanteRepository.updateVotante(id_votante, matricula, nome);
	}

//	Para caso seja adicionado o método de editar a senha do Votante.
//	public void validarAtualizacao(int id_votante, String matricula, String nome, String senha)
//			throws BusinessException {
//		if (votanteRepository.searchVotanteById(id_votante) == null) {
//			throw new BusinessException("Votante referido não encontrado");
//		}
//
//		if (matricula.isBlank()) {
//			throw new BusinessException("A matrícula deve ser preenchida.");
//		}
//
//		if (nome.isBlank()) {
//			throw new BusinessException("O nome deve ser preenchido.");
//		}
//
//	}
	
	public void validarAtualizacao(int id_votante, String matricula, String nome)
			throws BusinessException, SQLException {
		
//		if (verificarSeMatriculaExiste(matricula)) {
//			throw new BusinessException("A matrícula já existe.");
//		}
		
		
		if (matricula == null) {
			throw new BusinessException("Não é possível editar um Votante sem sua matrícula");
		}
		
		if (votanteRepository.searchVotanteById(id_votante) == null) {
			throw new BusinessException("Votante referido não encontrado");
		}

		if (matricula.isBlank()) {
			throw new BusinessException("A matrícula deve ser preenchida.");
		}

		if (nome.isBlank()) {
			throw new BusinessException("O nome deve ser preenchido.");
		}
		
	    if (verificarSeMatriculaExisteParaOutroVotante(id_votante, matricula)) {
	        throw new BusinessException("A matrícula já existe para outro Votante.");
	    }

	}
	
	public boolean verificarSeMatriculaExisteParaOutroVotante(int id_votante, String matricula) throws SQLException {
	    Votante votanteExistente = votanteRepository.searchVotanteByMatricula(matricula);

	    return votanteExistente != null && votanteExistente.getId_votante() != id_votante;
	}

	public boolean verificarSeMatriculaExiste(String matricula) throws SQLException {
		
		if (matriculasTemporarias.contains(matricula)) {
            return true;
        }
		
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT matricula \r\n" + "FROM votante \r\n");
			while (rs.next()) {
				String matriculaBd = rs.getString("matricula");
				boolean check = matriculaBd.equals(matricula);
				if (check == true) {
					return check;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(st);
		}
		return false;
	}
	
	
	
	
	public void verificarloginvot(String loginDigitada) throws BusinessException, SQLException {
		VotanteDAO.verificarloginvot(loginDigitada);
		Votante votante = votanteRepository.searchVotanteByMatricula(loginDigitada);
		if (votante == null) {
	        throw new BusinessException("login não encontrada");
	    }
	}

	public void verificarsenhavot(String loginDigitada, String senhaDigitada) throws BusinessException, SQLException {
		VotanteDAO.verificarsenhavot(loginDigitada, senhaDigitada);   
	}
	
	public Votante buscarVotante(String matricula) {
		
		return votanteRepository.searchVotanteByMatricula(matricula);
		
	}
}
