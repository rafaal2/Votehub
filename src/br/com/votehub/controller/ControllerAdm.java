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
	
	
	public void registrarAdm(int id_adm, String login, String senha)
			throws BusinessException {
		this.validarRegistro(id_adm, login, senha);

		Adm a = new Adm(id_adm, login, senha);
		AdmRepository.addAdm(a);
	}
	public void validarRegistro(int id_adm, String login, String senha) throws BusinessException {
		if(login.isBlank() || senha.isBlank()) {			
			throw new BusinessException("Todos os campos devem estar preenchindos!");			
		}
		
		if(login.length() > 100 || senha.length() > 100) {
			throw new BusinessException("Os valores login e senha informados devem possuir limite de 100 caracteres");
		}
		
		if(login.length() > 200) {
			throw new BusinessException("O login deve possuir limite de 200 caracteres!");
		}
	}
	public void deletarAdm(int id_adm) throws BusinessException {
		AdmRepository.deleteAdm(id_adm);
	}
	//public void validarExclusao(int id_adm) throws BusinessException {
		//if (AdmRepository.searchVotanteById(id_adm) == null) {
			//throw new BusinessException("Adm referido não encontrado!");}
	public void atualizarAdm(int id_adm, String login, String senha)
			throws BusinessException {
		validarAtualizacao(id_adm, login, senha);

		AdmRepository.updateAdm(id_adm, login, senha);
	}
	public void validarAtualizacao(int id_adm, String login, String senha)
			throws BusinessException {
		//if (AdmRepository.searchVotanteById(id_votante) == null) {
			//throw new BusinessException("Votante referido não encontrado");
		//}

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
				String matriculabd = encrip.encriptadorDeValores(rs.getString("login"), "d");
				boolean check = logindigit.equals(matriculabd);
				if (check == true) {
					return check;
				} else {
					System.out.println("login incorreta");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closestatement((java.sql.Statement) st);

		}
		return false;
	}

	public void validarVerificarloginadm(String logindigit) throws BusinessException {
		if (logindigit.isBlank()) {
			throw new BusinessException("Todos os campos devem estar preenchidos!");
		}

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
				String senhabd = encrip.encriptadorDeValores(rs.getString("senha"), "d");
				boolean check = senhadigit.equals(senhabd);
				if (check == true) {
					return check;
				} else {
					System.out.println("senha incorreta");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closestatement((java.sql.Statement) st);

		}
		return false;
	}
	
	
	
	
	
	
	
	
	

}