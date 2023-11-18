package br.com.votehub.model.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.votehub.model.vo.Candidato;

public class candidatoDAO {
	
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement stt = null;
	PreparedStatement stt1 = null;
	PreparedStatement stt2 = null;

	public void mostrarCandidatos() {
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * \r\n" + "FROM candidato \r\n");
			while (rs.next()) {
				System.out.println("Candidato: " + rs.getString("nome") + " / " + rs.getString("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closestatement(st);
			DB.closeConnection();
		}

	}

	public void addCandidato(Candidato c) {
		try {
			conn = DB.getConnection();
			stt = conn.prepareStatement("INSERT INTO candidato" + "(numero_candidato, nome, cargo)" + "VALUES" + "(?, ?, ?)");

			stt.setString(1, c.getNumero_candidato());
			stt.setString(2, c.getNome());
			stt.setString(3, c.getCargo());

			stt.executeUpdate();
			System.out.println("Novo candidato cadastrado");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt);
			DB.closeConnection();
		}

	}

	public void updateCandidato() {
		try {
			conn = DB.getConnection();
			stt1 = conn.prepareStatement("Update candidato " + "SET nome = ?" + "WHERE" + "(id = ?)");

			stt1.setString(1, "Teste");
			stt1.setInt(2, 2);

			stt1.executeUpdate();
			System.out.println("Informações atualizadas!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt1);
			DB.closeConnection();
		}
	}

	public void deleteCandidato() {
		try {
			conn = DB.getConnection();
			stt2 = conn.prepareStatement("DELETE FROM candidato " + "WHERE " + "id = ?");

			stt2.setInt(1, 5);

			stt2.executeUpdate();
			System.out.println("candidato deletado");
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closestatement(stt2);
			DB.closeConnection();
		}
	}

}
