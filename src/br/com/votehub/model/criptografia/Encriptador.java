package br.com.votehub.model.criptografia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jasypt.util.text.BasicTextEncryptor;

import br.com.votehub.model.DAOs.DB;

public class Encriptador {

	private static final String passWD = CriptografiaVotante.obterChaveSecreta();

	public String encriptadorDeValores(String senha, String operacao) {

		BasicTextEncryptor encriptadorDecriptador = new BasicTextEncryptor();
		encriptadorDecriptador.setPassword(passWD);
		if (operacao.equals("C")) {
			String valorEncrpitografado;
			valorEncrpitografado = encriptadorDecriptador.encrypt(senha);
			return valorEncrpitografado;
		} else {
			String valorDesencriptografado;
			valorDesencriptografado = encriptadorDecriptador.decrypt(senha);
			return valorDesencriptografado;
			
		}
	}
	static Encriptador encrip = new Encriptador();
	public static boolean verificarsenhavot(String senhadigit) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		boolean senhaIncorreta = false;
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT senha \r\n" + "FROM votante \r\n");
			while (rs.next()){
				String senhabd = encrip.encriptadorDeValores(rs.getString("senha"), "d");
				boolean check = senhadigit.equals(senhabd);
				if (check == true) {
					return check;
				}else {
					senhaIncorreta = true;
				}
			}
			 if (senhaIncorreta) {
		            System.out.println("Senha incorreta");
		        }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closestatement(st);
		}
		return false;
	}
	public static boolean verificarloginvot(String logindigit) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		boolean loginIncorreto = false;
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT matricula \r\n" + "FROM votante \r\n");
			while (rs.next()){
				String matriculabd = encrip.encriptadorDeValores(rs.getString("matricula"), "d");
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
	
	public static boolean verificarsenhaadm(String senhadigit) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		boolean senhaIncorreta = false;
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT senha \r\n" + "FROM adm \r\n");
			while (rs.next()){
				String senhabd = encrip.encriptadorDeValores(rs.getString("senha"), "d");
				boolean check = senhadigit.equals(senhabd);
				if (check == true) {
					return check;
				}else {
					senhaIncorreta = true;
				}
			}
			 if (senhaIncorreta) {
		            System.out.println("Senha incorreta");
		        }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closestatement(st);
			
		}
		return false;
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
				String matriculabd = encrip.encriptadorDeValores(rs.getString("login"), "d");
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

