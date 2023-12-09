package br.com.votehub.model.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.jasypt.util.password.StrongPasswordEncryptor;

import br.com.votehub.controller.BusinessException;
import br.com.votehub.model.vo.Adm;
import br.com.votehub.model.vo.Votante;

public class AdmDAO {
	private final static StrongPasswordEncryptor passHash = new StrongPasswordEncryptor();
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement stt = null;
	PreparedStatement stt1 = null;
	PreparedStatement stt2 = null;
	public void addAdm(Adm a ) {
		try {
			StrongPasswordEncryptor senhacrip = new StrongPasswordEncryptor();
			conn = DB.getConnection();
			stt = conn.prepareStatement("INSERT INTO adm" + "(login, senha)" + "VALUES" + "(?, ?)");

			stt.setString(1, a.getLogin());
			stt.setString(2, senhacrip.encryptPassword(a.getSenha()));

			stt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt);
		//	DB.closeConnection();
		}

	}

	public void updateAdm(int Id_adm, String login, String senha) {
		try {
			StrongPasswordEncryptor senhacrip = new StrongPasswordEncryptor();
			conn = DB.getConnection();
			stt1 = conn.prepareStatement("Update adm " + "SET login = ?, senha = ?" + "WHERE" + "id_adm = ?");

			stt1.setString(1, login);
			stt1.setString(2, senhacrip.encryptPassword(senha));
			stt1.setInt(3, Id_adm);

			stt1.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt1);
		//	DB.closeConnection();
		}
	}

	public void deleteAdm(int Id_adm) {
		try {
			conn = DB.getConnection();
			stt2 = conn.prepareStatement("DELETE FROM adm " + "WHERE " + "id_adm = ?");

			stt2.setInt(1, Id_adm);

			stt2.executeUpdate();
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closestatement(stt2);
		//	DB.closeConnection();
		}
	}
	public static boolean verificarloginadm(String logindigit) throws SQLException, BusinessException {
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		boolean loginIncorreto = false;
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT login \r\n" + "FROM adm \r\n");
			while (rs.next()){
				String matriculabd = rs.getString("login");
				boolean check = logindigit.equals(matriculabd);
				if (check == true) {
					return check;
				}else {
					loginIncorreto = true;
				}
				if (logindigit.isBlank()) {
					throw new BusinessException("o campo de login deve estar preenchido");
				}
			}
			 if (loginIncorreto) {
				 throw new BusinessException("login incorreta");
		        
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closestatement(st);
			
		}
		return false;
	}
	public static boolean verificarsenhaadm(String senhaDigitada) throws BusinessException, SQLException {
		Connection conn = null;
		ResultSet rs = null;
		java.sql.Statement st = null;
		boolean senhaIncorreta = false;
		try {
			conn = DB.getConnection();
			st =  conn.createStatement();
			rs =  st.executeQuery("SELECT senha \r\n" + "FROM adm \r\n");
			while (rs.next()) {
				String senhabd = rs.getString("senha");
				boolean check = passHash.checkPassword(senhaDigitada, senhabd);
				if (check) {
					return true;
				}if(senhaDigitada.isBlank()) {
					throw new BusinessException("o campo da senha deve estar preenchido");
				}if(senhaDigitada.length() < 10) {
					throw new BusinessException("a senha deve conter no minimo 11 caracteres");
				}else {
					senhaIncorreta = true;}
			        throw new BusinessException("Senha incorreta");
		}}
		 catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closestatement((java.sql.Statement) st);

		}
		return false;
	}
	
	public Adm searchAdmByLogin(String login) {
		try {

			conn = DB.getConnection();
			PreparedStatement stt = conn
					.prepareStatement("SELECT id_adm, login, senha FROM adm WHERE login = ?");

			stt.setString(1, login);

			ResultSet rs = stt.executeQuery();
			if (rs.next()) {
				Adm adm = new Adm(rs.getString("login"), rs.getString("senha"));
				adm.setId_adm(rs.getInt("id_adm"));
				return adm;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean verificarSeIdExiste(int idADM) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT id_adm \r\n" + "FROM adm \r\n");
			while (rs.next()) {
				String idAdm = Integer.toString(rs.getInt("id_adm"));
				boolean check = idAdm.equals(idADM);
				if (check == true) {
					return check;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			//DB.closestatement(st);
			
		}
		return false;
	}
}
