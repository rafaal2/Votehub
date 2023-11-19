package br.com.votehub.model.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import br.com.votehub.model.vo.*;

public class VotoDAO {
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement stt = null;
	PreparedStatement stt1 = null;
	PreparedStatement stt2 = null;

	public void addVoto(Voto vt) {
		try {
			conn = DB.getConnection();
			stt = conn.prepareStatement("INSERT INTO voto" + "(id_voto, numero_candidato, id_votante)" + "VALUES" + "(?, ?, ?)");

			stt.setInt(1, vt.getId_voto());
			stt.setString(2, vt.getNumero_candidato());
			stt.setInt(3, vt.getId_votante());

			stt.executeUpdate();
			System.out.println("novo voto cadastrado");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt);
			DB.closeConnection();
		}

		
	}
	
	public void deleteVoto(int id) { //pode ser substituido por um metodo "anularVoto()" futuramente
		try {
			conn = DB.getConnection();
			stt2 = conn.prepareStatement("DELETE FROM voto " 
										+"WHERE " 
										+"id_voto = ?");
			
			stt2.setInt(1, id);
			
			stt2.executeUpdate();
			System.out.println("voto deletado!");
		}
		catch(SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}
		finally {
			DB.closestatement(stt2);
			DB.closeConnection();
		}
	}
	
	public void apurarVotos() {
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * \r\n"
					+ "FROM voto \r\n");
			while(rs.next()) {
				System.out.println("Voto: " + rs.getInt("id_voto") + " | Candidato " + rs.getInt("numero_candidato"));
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
}
