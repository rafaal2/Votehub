package br.com.votehub.model.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.jasypt.util.password.StrongPasswordEncryptor;

import br.com.votehub.model.criptografia.Encriptador;
import br.com.votehub.model.vo.Adm;

public class AdmDAO {
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
			System.out.println("Novo admin cadastrado");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt);
			DB.closeConnection();
		}

	}

	public void updateAdm(int Id_adm, String login, String senha) {
		try {
			conn = DB.getConnection();
			stt1 = conn.prepareStatement("Update adm " + "SET login = ?, senha = ?" + "WHERE" + "id_adm = ?");

			stt1.setString(1, login);
			stt1.setString(2, senha);
			stt1.setInt(3, Id_adm);

			stt1.executeUpdate();
			System.out.println("Informações do admin " + Id_adm + "atualizadas!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt1);
			DB.closeConnection();
		}
	}

	public void deleteAdm(int Id_adm) {
		try {
			conn = DB.getConnection();
			stt2 = conn.prepareStatement("DELETE FROM adm " + "WHERE " + "id_adm = ?");

			stt2.setInt(1, Id_adm);

			stt2.executeUpdate();
			System.out.println("adm" + Id_adm+ "deletado");
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closestatement(stt2);
			DB.closeConnection();
		}
	}
	public static boolean verificarloginadm(String logindigit) throws SQLException {
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
			}
			 if (loginIncorreto) {
		            System.out.println("login incorreto");
		        
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closestatement(st);
			
		}
		return false;
	}

}
