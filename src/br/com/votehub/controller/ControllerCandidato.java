package br.com.votehub.controller;

import br.com.votehub.model.DAOs.AdmDAO;
import br.com.votehub.model.DAOs.CandidatoDAO;
import br.com.votehub.model.DAOs.DB;
import br.com.votehub.model.vo.Candidato;
import br.com.votehub.model.vo.Votante;

import java.sql.*;
import java.util.List;

public class ControllerCandidato {
	private CandidatoDAO candidatoRepository = new CandidatoDAO();

	public void registrarCandidato(String numeroCandidato, String nome, String cargo, int id_votacao, String img_candidato)
			throws BusinessException, SQLException {
		validarRegistro(numeroCandidato, nome, cargo, id_votacao, img_candidato);

		Candidato cd = new Candidato(numeroCandidato, nome, cargo, id_votacao, img_candidato);
		candidatoRepository.addCandidato(cd);
	}

	public void validarRegistro(String numeroCandidato, String nome, String cargo, Integer id_votacao, String img_candidato) throws BusinessException, SQLException {
		if (candidatoRepository.verificarSeNumeroExiste(numeroCandidato)) {
			throw new BusinessException("Número do candidato já está em uso!");
		}
		

		if (numeroCandidato.isBlank() || nome.isBlank() || cargo.isBlank() || img_candidato == null) {
			throw new BusinessException("Todos os campos devem estar preenchindos!");
		}

		if (verificarSeNumeroDeCandidatoExiste(numeroCandidato)) {
			throw new SQLException("Número de candidato existente.");
		}

		if (numeroCandidato.length() > 100 || cargo.length() > 100) {
			throw new BusinessException("Os valores numero e cargo informados devem possuir limite de 100 caracteres");
		}

		if (nome.length() > 200) {
			throw new BusinessException("O nome inserido deve possuir limite de 200 caracteres");
		}

	}

	public void deletarCandidato(String numeroCandidato) throws BusinessException {
		validarExclusao(numeroCandidato);

		candidatoRepository.deleteCandidato(numeroCandidato);
	}

	public void validarExclusao(String numeroCandidato) throws BusinessException {
		if (candidatoRepository.searchCandidatoById(numeroCandidato) == null) {
			throw new BusinessException("Candidato referido não encontrado!");
		}
	}

	public void atualizarCandidato(String numeroCandidato, String nome, String cargo, int id_votacao, String img_candidato)
			throws BusinessException {
		validarAtualizacao(numeroCandidato, nome, cargo, id_votacao, img_candidato);

//		String nomeC;
//		String cargoC;
//		int id_votacaoC = 0;
//		String img_candidatoC;
//
//		Candidato cddt = candidatoRepository.searchCandidatoById(numeroCandidato);
//
//		if (nome.isBlank()) {
//			nomeC = cddt.getNome();
//		} else {
//			nomeC = nome;
//		}
//
//		if (cargo.isBlank()) {
//			cargoC = cddt.getCargo();
//		} else {
//			cargoC = cargo;
//		}
//		if (img_candidato.isBlank()) {
//			img_candidatoC = cddt.getImg_candidato();
//		} else {
//			img_candidatoC = img_candidato;
//		}

		candidatoRepository.updateCandidato(numeroCandidato, nome, cargo, id_votacao, img_candidato);

	}

	public void validarAtualizacao(String numeroCandidato, String nome, String cargo, int id_votacao, String img_candidato) throws BusinessException {
		if (candidatoRepository.searchCandidatoById(numeroCandidato) == null) {
			throw new BusinessException("Candidato referido não encontrado");

		}

		if (numeroCandidato.length() > 100 || cargo.length() > 100) {
			throw new BusinessException("Os valores numero e cargo informados devem possuir limite de 100 caracteres");
		}

		if (nome.length() > 200) {
			throw new BusinessException("O nome inserido deve possuir limite de 200 caracteres!");
		}

	}

	public boolean verificarSeNumeroDeCandidatoExiste(String numeroCandidato) throws SQLException {

		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;

		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT numero_candidato \r\n" + "FROM candidato \r\n");
			while (rs.next()) {
				String numeroCandidatoBd = rs.getString("numero_candidato");
				boolean check = numeroCandidatoBd.equals(numeroCandidato);
				if (check == true) {
					return check;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Candidato> ExibirCandidatos() {
		return candidatoRepository.obterTodosCandidatos();
		
	}

	public ResultSet exibirReitor() {

		return candidatoRepository.addCandidatosReitorCombobox();

	}

	public ResultSet exibirDiretor() {

		return candidatoRepository.addCandidatosDiretorCombobox();

	}
	
	public String recuperarImg() {
		String numeroCandidato = "1";
		return candidatoRepository.searchCandidatoImg(numeroCandidato);
	}
	
	public Candidato buscarCandidato(String numeroCandidato) {
		
		return candidatoRepository.searchCandidatoById(numeroCandidato);
		
	}

	public void setCandidatoRepository(CandidatoDAO candidatoRepository2) {
		this.candidatoRepository = candidatoRepository2;
		
	}

}