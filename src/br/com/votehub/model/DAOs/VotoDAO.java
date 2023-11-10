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

	public void addvoto(Voto vt) {
		try {
			conn = DB.getConnection();
			stt = conn.prepareStatement("INSERT INTO voto" + "(id, id_candidato, id_votante)" + "VALUES" + "(?, ?, ?)");

			stt.setInt(1, vt.getId());
			stt.setInt(2, vt.getId_candidato());
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
}
