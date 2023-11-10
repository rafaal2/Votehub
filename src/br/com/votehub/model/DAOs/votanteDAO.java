package br.com.votehub.model.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.votehub.model.vo.Votante;


public class votanteDAO {	
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement stt = null;
		PreparedStatement stt1 = null;
		PreparedStatement stt2 = null;
		
		public void mostarvotantes() {
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * \r\n"
					+ "FROM votante \r\n");
			while(rs.next()) {
				System.out.println("Votante: " + rs.getString("nome") + " / " + rs.getString("ocupação"));
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
		public void addvotante(Votante v) {
			try {
				conn = DB.getConnection();
				stt = conn.prepareStatement("INSERT INTO votante" + "(id, nome, cpf, ocupação)" + "VALUES" +"(?, ?, ?, ?)");
				
				stt.setInt(1, v.getId());
				stt.setString(2, v.getNome());
				stt.setString(3, v.getcpf());
				stt.setString(4, v.getOcupacao());
				
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
		public void updatevotante() {
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
		public void deletevotante() {
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

