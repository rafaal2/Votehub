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
			stt = conn.prepareStatement("INSERT INTO voto" + "(numero_candidato)" + "VALUES" + "(?)");

			stt.setString(1, vt.getNumero_candidato());

			stt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt);
		}
	}
	
	public void deleteVoto(int id_voto) { //pode ser substituido por um metodo "anularVoto()" futuramente
		try {
			conn = DB.getConnection();
			stt2 = conn.prepareStatement("DELETE FROM voto " 
										+"WHERE " 
										+"id_voto = ?");
			
			stt2.setInt(1, id_voto);
			
			stt2.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}
		finally {
			DB.closestatement(stt2);
	//		DB.closeConnection();
		}
	}
	
	public void apurarVotos() {
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * \r\n"
					+ "FROM voto \r\n");
			while(rs.next()) {
			}}
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				DB.closeResultSet(rs);
				DB.closestatement(st);
		//		DB.closeConnection();
			}
			
		}
	
	public Voto searchVotoById(int idVoto) {
		try {
			conn = DB.getConnection();
			stt = conn.prepareStatement("SELECT * FROM voto " + "WHERE " + "id_voto = ?");

			stt.setInt(1, idVoto);
			
			rs = stt.executeQuery();
			if(rs.next()) {
				
			Voto vt = new Voto(rs.getString("numero_candidato"));
			
			return vt;
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt);
		}
		return null;
	}	
}
