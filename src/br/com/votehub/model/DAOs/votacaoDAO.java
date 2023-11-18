package br.com.votehub.model.DAOs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import br.com.votehub.model.vo.Votação;

public class votacaoDAO {
	
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement stt = null;
	PreparedStatement stt1 = null;
	PreparedStatement stt2 = null;
	
	public void addVotacao(Votação votacao) {
		try {
			conn = DB.getConnection();
			stt = conn.prepareStatement("INSERT INTO votacao" + "(data_inicio, data_fim)" + "VALUES" + "(?, ?)");

			
			// timestamp é utilizado p colunas "datatime" no sql;
			Timestamp dataInicioTimestamp = new Timestamp(votacao.getData_inicio().getTime());
	        Timestamp dataFimTimestamp = new Timestamp(votacao.getData_fim().getTime());
	        
			stt.setTimestamp(1, dataInicioTimestamp);
			stt.setTimestamp(2, dataFimTimestamp);
			stt.executeUpdate();
			System.out.println("Nova votação cadastrada");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt);
			DB.closeConnection();
		}

	}
	
	public void updateVotacao(int idVotacao, Date dataInicio, Date dataFim) {
		try {
			conn = DB.getConnection();
			stt1 = conn.prepareStatement("Update votação " + "SET data_inicio = ?, data_fim = ?" + "WHERE" + "id_votação = ?");

			Timestamp dataInicioTimestamp = new Timestamp(dataInicio.getTime());
	        Timestamp dataFimTimestamp = new Timestamp(dataFim.getTime());
			

	        stt1.setTimestamp(1, dataInicioTimestamp);
	        stt1.setTimestamp(2, dataFimTimestamp);
	        stt1.setInt(3, idVotacao);

			stt1.executeUpdate();
			System.out.println("Informações atualizadas!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt1);
			DB.closeConnection();
		}
	}
	
	public void deleteVotacao(String idVotacao) {
		try {
			conn = DB.getConnection();
			stt2 = conn.prepareStatement("DELETE FROM votação " + "WHERE " + "id = ?");

			stt2.setString(1, idVotacao);

			stt2.executeUpdate();
			System.out.println("Votação deletada");
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closestatement(stt2);
			DB.closeConnection();
		}
	}

}
