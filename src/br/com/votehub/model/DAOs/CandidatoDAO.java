package br.com.votehub.model.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

import br.com.votehub.model.vo.Candidato;

public class CandidatoDAO {
	
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
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closestatement(st);
			//DB.closeConnection();
		}

	}

	public void addCandidato(Candidato c) {
		try {
			conn = DB.getConnection();
			stt = conn.prepareStatement("INSERT INTO candidato" + "(numero_candidato, nome, cargo, id_votacao, img_candidato)" + "VALUES" + "(?, ?, ?, ?, ?)");

			stt.setString(1, c.getNumero_candidato());
			stt.setString(2, c.getNome());
			stt.setString(3, c.getCargo());
			stt.setInt(4, c.getId_votacao());
			stt.setString(5, c.getImg_candidato());

			stt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt);
			//DB.closeConnection();
		}

	}

	public void updateCandidato(String numeroCandidato, String nome, String cargo, int id_votacao, String img_candidato) {
		try {
			conn = DB.getConnection();
			stt1 = conn.prepareStatement("Update candidato " + "SET nome = ?, cargo = ?, id_votacao = ?, img_candidato = ? " + "WHERE" + " numero_candidato = ?");

			stt1.setString(1, nome);
			stt1.setString(2, cargo);
			stt1.setInt(3, id_votacao);
			stt1.setString(4, img_candidato);
			stt1.setString(5, numeroCandidato);

			stt1.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt1);
			//DB.closeConnection();
		}
	}
	
	

	public void deleteCandidato(String numeroCandidato) {
		try {
			conn = DB.getConnection();
			stt2 = conn.prepareStatement("DELETE FROM candidato " + "WHERE " + "numero_candidato = ?");

			stt2.setString(1, numeroCandidato);

			stt2.executeUpdate();
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closestatement(stt2);
			//DB.closeConnection();
		}
	}
	
	public Candidato searchCandidatoById(String numeroCandidato) {
		try {
			conn = DB.getConnection();
			stt = conn.prepareStatement("SELECT * FROM candidato " + "WHERE " + "numero_candidato = ?");

			stt.setString(1, numeroCandidato);
			
			rs = stt.executeQuery();
			if(rs.next()) {
				
			Candidato c = new Candidato(rs.getString("numero_candidato"), rs.getString("nome"), rs.getString("cargo"), rs.getInt("id_votacao"),rs.getString("img_candidato"));
			
			return c;
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt);
			//,DB.closeConnection();
		}
		return null;

	}	
	
	public boolean verificarSeNumeroExiste(String numeroCandidato) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT numero_candidato \r\n" + "FROM candidato \r\n");
			while (rs.next()) {
				String numeroBd = rs.getString("numero_candidato");
				boolean check = numeroBd.equals(numeroCandidato);
				if (check == true) {
					return check;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			//DB.closestatement(st);
			
		}
		return false;
	}
	
	public ResultSet addCandidatosReitorCombobox() {
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM candidato where cargo = \"Reitor\"");
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
//			DB.closeResultSet(rs);
//			DB.closestatement(st);
//			DB.closeConnection();
		}

	}
	
	public ResultSet addCandidatosDiretorCombobox() {
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM candidato where cargo = \"Diretor\"");
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
//			DB.closeResultSet(rs);
//			DB.closestatement(st);
//			DB.closeConnection();
		}

	}
	public String searchCandidatoImg(String numeroCandidato) {
		try {
			conn = DB.getConnection();
			stt = conn.prepareStatement("SELECT * FROM candidato " + "WHERE " + "numero_candidato = ?");

			stt.setString(1, numeroCandidato);
			
			rs = stt.executeQuery();
			if(rs.next()) {
				
			Candidato c = new Candidato(rs.getString("numero_candidato"), rs.getString("nome"), rs.getString("cargo"), rs.getInt("id_votacao"),rs.getString("img_candidato"));
			
			return c.getImg_candidato();
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt);
			//,DB.closeConnection();
		}
		return null;

	}	

}
