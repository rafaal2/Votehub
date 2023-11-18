package br.com.votehub.model.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.votehub.model.criptografia.Encriptador;
import br.com.votehub.model.vo.Votante;


public class votanteDAO {	
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement stt = null;
		PreparedStatement stt1 = null;
		PreparedStatement stt2 = null;
		
		public void mostrarVotantes() {
			Encriptador encrip = new Encriptador();
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * \r\n"
					+ "FROM votante \r\n");
			while(rs.next()) {
				String encryptedNome = encrip.encriptadorDeValores(rs.getString("nome"), "d");
				System.out.println("Votante: " + encryptedNome + " / " + rs.getString("ocupação"));
			}}
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				DB.closeResultSet(rs);
				DB.closestatement(st);
				DB.closeConnection();
			}
			
		}
		public void addVotante(Votante v) {
			Encriptador encrip = new Encriptador();
			try {
				conn = DB.getConnection();
				stt = conn.prepareStatement("INSERT INTO votante" + "(id_votante, matricula, nome, senha, ocupação)" + "VALUES" +"(?, ?, ?, ?, ?)");
				
				stt.setInt(1, v.getId_votante());
				stt.setString(2, encrip.encriptadorDeValores(v.getMatricula(), "C" ));
				stt.setString(3, encrip.encriptadorDeValores(v.getNome(), "C" ));
				stt.setString(4, encrip.encriptadorDeValores(v.getSenha(), "C" ));
				stt.setString(5, encrip.encriptadorDeValores(v.getOcupação(), "C" ));
				
				stt.executeUpdate();
				System.out.println("novo votante cadastrado");
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				DB.closestatement(stt);
				DB.closeConnection();
			}
	
		}
		public void updateVotante() {
			try {
				conn = DB.getConnection();
				stt1 = conn.prepareStatement("Update votante " 
											+ "SET cpf = ?" 
											+ "WHERE" 
											+"(id = ?)");
				
				stt1.setString(1, "15111111");
				stt1.setInt(2, 2);
				
				stt1.executeUpdate();
				System.out.println("informações atualizadas!");
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				DB.closestatement(stt1);
				DB.closeConnection();
			}
		}
		public void deleteVotante() {
			try {
				conn = DB.getConnection();
				stt2 = conn.prepareStatement("DELETE FROM votante " 
											+"WHERE " 
											+"id = ?");
				
				stt2.setInt(1, 9);
				
				stt2.executeUpdate();
				System.out.println("votante deletado");
			}
			catch(SQLException e) {
				throw new DbIntegrityException(e.getMessage());
			}
			finally {
				DB.closestatement(stt2);
				DB.closeConnection();
			}
		}
	


}


