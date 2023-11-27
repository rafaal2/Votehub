package br.com.votehub.controller;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.votehub.model.DAOs.AdmDAO;
import br.com.votehub.model.DAOs.DB;
import br.com.votehub.model.criptografia.Encriptador;
import br.com.votehub.model.vo.Adm;

public class ControllerAdm {
	private AdmDAO AdmRepository = new AdmDAO();

	public void registrarAdm(int id_adm, String login, String senha) throws BusinessException {
		this.validarRegistro(id_adm, login, senha);

		Adm a = new Adm(login, senha);
		AdmRepository.addAdm(a);
	}

	public void validarRegistro(int id_adm, String login, String senha) throws BusinessException {
		if (login.isBlank() || senha.isBlank()) {
			throw new BusinessException("Todos os campos devem estar preenchindos!");
		}

		if (login.length() > 100 || senha.length() > 100) {
			throw new BusinessException("Os valores login e senha informados devem possuir limite de 100 caracteres");
		}

		if (login.length() > 200) {
			throw new BusinessException("O login deve possuir limite de 200 caracteres!");
		}
	}

	public void deletarAdm(int id_adm) throws BusinessException {
		AdmRepository.deleteAdm(id_adm);
	}

	// public void validarExclusao(int id_adm) throws BusinessException {
	// if (AdmRepository.searchVotanteById(id_adm) == null) {
	// throw new BusinessException("Adm referido não encontrado!");}
	public void atualizarAdm(int id_adm, String login, String senha) throws BusinessException {
		validarAtualizacao(id_adm, login, senha);

		AdmRepository.updateAdm(id_adm, login, senha);
	}

	public void validarAtualizacao(int id_adm, String login, String senha) throws BusinessException {
		// if (AdmRepository.searchVotanteById(id_votante) == null) {
		// throw new BusinessException("Votante referido não encontrado");
		// }

		if (id_adm != 0 || login.isBlank() || senha.isBlank()) {
			throw new BusinessException("Todos os campos devem estar preenchidos!");
		}

	}

	static Encriptador encrip = new Encriptador();

	public boolean verificarloginadm(String logindigit) throws BusinessException, SQLException {
		Connection conn = null;
		ResultSet rs = null;
		java.sql.Statement st = null;
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT login \r\n" + "FROM adm \r\n");
			while (rs.next()) {
				String matriculabd = rs.getString("login");
				boolean check = logindigit.equals(matriculabd);
				if (check) {
					return true;
				}
				if (logindigit.isBlank()) {
					throw new BusinessException("o campo de login deve estar preenchido");
				}
				throw new BusinessException("login incorreta");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closestatement((java.sql.Statement) st);

		}
		return false;
	}

	public boolean verificarsenhaadm(String senhadigit) throws BusinessException, SQLException {
		Connection conn = null;
		ResultSet rs = null;
		java.sql.Statement st = null;
		try {
			conn = DB.getConnection();
			st =  conn.createStatement();
			rs =  st.executeQuery("SELECT senha \r\n" + "FROM adm \r\n");
			while (rs.next()) {
				String senhabd = rs.getString("senha");
				boolean check = senhadigit.equals(senhabd);
				if (check) {
					return true;
				}if(senhadigit.isBlank()) {
					throw new BusinessException("o campo da senha deve estar preenchido");
				}if(senhadigit.length() < 10) {
					throw new BusinessException("a senha deve conter no minimo 11 caracteres");
				}}
			throw new BusinessException("Senha incorreta");
		}
		 catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closestatement((java.sql.Statement) st);

		}
		return false;
	}

	public void validarVerificarloginadm(String logindigit, String senhadigit) throws BusinessException {
		if (logindigit.isBlank() || senhadigit.isBlank()) {
			throw new BusinessException("Todos os campos devem estar preenchidos!");
		}
		if (logindigit.length() > 100 || senhadigit.length() > 100) {
			throw new BusinessException("Os valores numero e cargo informados devem possuir limite de 100 caracteres");
		}

	}

}
