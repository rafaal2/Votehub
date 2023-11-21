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
	public static boolean verificarsenha(String senhadigit) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
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
					System.out.println("senha incorreta");
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

