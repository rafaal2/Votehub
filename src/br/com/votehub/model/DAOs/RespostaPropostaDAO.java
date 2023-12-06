package br.com.votehub.model.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import br.com.votehub.model.vo.RespostaProposta;

public class RespostaPropostaDAO {
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement stt = null;
	PreparedStatement stt1 = null;
	PreparedStatement stt2 = null;

	public void mostrarRespostaProposta() {
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * \r\n" + "FROM respostaproposta \r\n");
			while (rs.next()) {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closestatement(st);
			// DB.closeConnection();
		}

	}

	public void addRespostaProposta(RespostaProposta pp) {
		try {
			conn = DB.getConnection();
			stt = conn.prepareStatement(
					"INSERT INTO respostaproposta" + "(resposta, id_proposta)" + "VALUES" + "(?, ?)");

			stt.setString(1, pp.getResposta());
			stt.setInt(2, pp.getId_Proposta());

			stt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt);
			// DB.closeConnection();
		}

	}

	public void deleteRespostaProposta(int id_RespostaProposta) {
		try {
			conn = DB.getConnection();
			stt2 = conn.prepareStatement("DELETE FROM respostaproposta " + "WHERE " + "id_respostaproposta = ?");

			stt2.setInt(1, id_RespostaProposta);

			stt2.executeUpdate();
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closestatement(stt2);
			// DB.closeConnection();
		}
	}

	public RespostaProposta searchRespostaPropostaById(int id_RespostaProposta) {
		try {
			conn = DB.getConnection();
			stt = conn.prepareStatement("SELECT * FROM respostaproposta " + "WHERE " + "id_respostaproposta = ?");

			stt.setInt(1, id_RespostaProposta);

			rs = stt.executeQuery();
			if (rs.next()) {

				RespostaProposta pp = new RespostaProposta(rs.getString("resposta"), rs.getInt("id_proposta"));

				return pp;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt);
			// DB.closeConnection();
		}
		return null;

	}

}
