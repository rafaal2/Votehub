package br.com.votehub.model.criptografia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jasypt.util.password.StrongPasswordEncryptor;

import br.com.votehub.model.DAOs.DB;

public class Hash {	
	/*Algorithm: SHA-256. o GPT é ?*/
//	Salt size: 16 bytes. Random
//	Iterations: 100000.  
	
	private final static StrongPasswordEncryptor passHash = new StrongPasswordEncryptor();
	
	public static String gerarHash(String senha) { //Deve ser implementado no VotanteDAO
		String senhaHash = passHash.encryptPassword(senha);  
		return senhaHash;
	}
	
	public static boolean verificarHash(int id, String senhaDigitada) throws SQLException {
		Connection conn = null;             //   Selecionando Votante pelo ID
		ResultSet rs = null;
		PreparedStatement ps = null;
		//Encriptador encriptador = new Encriptador();
		//nome = encriptador.encriptadorDeValores(nome, "C");
		//System.out.println(nome);
		try {
			conn = DB.getConnection();
			String query = "SELECT * FROM votante WHERE id_votante = ?";
			ps = conn.prepareStatement(query);
	        ps.setInt(1, id);
			rs = 	ps.executeQuery();
			while(rs.next()) {
				String senhabd = rs.getString("senha");
				boolean check = passHash.checkPassword(senhaDigitada, senhabd);
				if (check) {
		            return true;
		        }	
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeResultSet(rs);
			DB.closestatement(ps);
			DB.closeConnection();
		}
		return false;
	}
}
