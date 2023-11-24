package br.com.votehub.controller;

import br.com.votehub.model.vo.Votante;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.votehub.model.DAOs.*;
import br.com.votehub.model.criptografia.Encriptador;

public class ControllerVotante {

	private VotanteDAO votanteRepository = new VotanteDAO();

	// resolver questão do id_votante, que é auto increment no banco de dados.//ja foi resolvido piranha
	public void registrarVotante(String matricula, String nome, String senha, String ocupacao) throws BusinessException, SQLException {
		this.validarRegistro( matricula, nome, senha, ocupacao);

		Votante vt = new Votante( matricula, nome, senha, ocupacao);
		votanteRepository.addVotante(vt);
	}

	public void validarRegistro( String matricula, String nome, String senha, String ocupacao) throws BusinessException, SQLException {

		if (matricula.isBlank()) {
			throw new BusinessException("A matrícula deve ser preenchida.");
		}

		if (verificarSeMatriculaExiste(matricula)) {
			throw new SQLException("A matrícula já é existente");
		}

		if (nome.isBlank()) {
			throw new BusinessException("O nome deve ser preenchido.");
		}

		if (senha.isBlank()) {
			throw new BusinessException("A senha deve ser preenchida.");
		}

		if (ocupacao.isBlank()) {
			throw new BusinessException("A ocupação deve ser preenchida.");
		}
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

	public void atualizarVotante(int id_votante, String matricula, String nome, String senha, String ocupacao)
			throws BusinessException {
		validarAtualizacao(id_votante, matricula, nome, senha, ocupacao);

		votanteRepository.updateVotante(id_votante, matricula, nome, senha, ocupacao);
	}

	public void validarAtualizacao(int id_votante, String matricula, String nome, String senha, String ocupacao)
			throws BusinessException {
		if (votanteRepository.searchVotanteById(id_votante) == null) {
			throw new BusinessException("Votante referido não encontrado");
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

		if (ocupacao.isBlank()) {
			throw new BusinessException("A ocupação deve ser preenchida.");
		}
	}

	public boolean verificarSeMatriculaExiste(String matricula) throws SQLException {
		Encriptador encrip = new Encriptador();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT matricula \r\n" + "FROM votante \r\n");
			while (rs.next()) {
				String matriculaBd = encrip.encriptadorDeValores(rs.getString("matricula"), "d");
				boolean check = matriculaBd.equals(matricula);
				if (check == true) {
					return check;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closestatement(st);
			DB.closeConnection();
		}
		return false;
	}
}
