package br.com.votehub.model.DAOs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import br.com.votehub.model.vo.Candidato;
import br.com.votehub.model.vo.Votacao;

public class VotacaoDAO {
	
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement stt = null;
	PreparedStatement stt1 = null;
	PreparedStatement stt2 = null;
	
	public void addVotacao(Votacao votacao) {
		try {
			conn = DB.getConnection();
			stt = conn.prepareStatement("INSERT INTO votacao" + "(nome_votacao, data_inicio, data_fim)" + "VALUES" + "(?, ?)");

			
			// timestamp é utilizado p colunas "datatime" no sql;
			Timestamp dataInicioTimestamp = new Timestamp(votacao.getData_inicio().getTime());
	        Timestamp dataFimTimestamp = new Timestamp(votacao.getData_fim().getTime());
	        
	        stt.setString(1, votacao.getNome_votacao());
			stt.setTimestamp(2, dataInicioTimestamp);
			stt.setTimestamp(3, dataFimTimestamp);
			stt.executeUpdate();
			System.out.println("Nova votação cadastrada");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt);
			DB.closeConnection();
		}

	}
	
	public void updateVotacao(int idVotacao, String nome_votacao, java.util.Date dataInicio, java.util.Date dataFim) {
		try {
			conn = DB.getConnection();
			stt1 = conn.prepareStatement("Update votação " + "SET nome_votacao = ?, data_inicio = ?, data_fim = ?" + "WHERE" + "id_votação = ?");

			Timestamp dataInicioTimestamp = new Timestamp(dataInicio.getTime());
	        Timestamp dataFimTimestamp = new Timestamp(dataFim.getTime());
			
	        stt1.setString(1, nome_votacao);
	        stt1.setTimestamp(2, dataInicioTimestamp);
	        stt1.setTimestamp(3, dataFimTimestamp);
	        stt1.setInt(4, idVotacao);

			stt1.executeUpdate();
			System.out.println("Informações da votação " +idVotacao+ "atualizadas!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt1);
			DB.closeConnection();
		}
	}
	
	public void deleteVotacao(int idVotacao) {
		try {
			conn = DB.getConnection();
			stt2 = conn.prepareStatement("DELETE FROM votação " + "WHERE " + "id = ?");

			stt2.setInt(1, idVotacao);

			stt2.executeUpdate();
			System.out.println("Votação " +idVotacao+ "deletada");
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closestatement(stt2);
			DB.closeConnection();
		}
	}
	
	public Votacao searchVotacaoById(int idVotacao) {
		try {
			conn = DB.getConnection();
			stt = conn.prepareStatement("SELECT * FROM votação " + "WHERE " + "id_votação = ?");

			stt.setInt(1, idVotacao);
			
			rs = stt.executeQuery();
			if(rs.next()) {
				
			Votacao vtc = new Votacao(rs.getString("nome_votacao"), rs.getDate("data_inicio"), rs.getDate("data_fim"));
			
			return vtc;
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt);
			DB.closeConnection();
		}
		return null;

	}

}

