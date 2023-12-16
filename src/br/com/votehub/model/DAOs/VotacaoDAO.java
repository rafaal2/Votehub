package br.com.votehub.model.DAOs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.votehub.model.vo.Candidato;
import br.com.votehub.model.vo.Votacao;
import br.com.votehub.model.vo.Votante;

public class VotacaoDAO {
	
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement stt = null;
	PreparedStatement stt1 = null;
	PreparedStatement stt2 = null;
	
	
	public List<Votacao> obterTodasVotacoes() {
	    List<Votacao> votacoes = new ArrayList<>();

	    try {
	        conn = DB.getConnection();
	        stt = conn.prepareStatement("SELECT * FROM votacao");

	        rs = stt.executeQuery();

	        while (rs.next()) {
	            int id_votacao = rs.getInt("id_votacao");
	            String nome_votacao = rs.getString("nome_votacao");
	            Date data_inicio = rs.getDate("data_inicio");
	            Date data_fim = rs.getDate("data_fim");
	            String tipo_votacao = rs.getString("tipo_votacao");

	            Votacao votacao = new Votacao(nome_votacao, data_inicio, data_fim, tipo_votacao);
	            votacao.setId_votacao(id_votacao);

	            votacoes.add(votacao);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DB.closeResultSet(rs);
	        DB.closestatement(stt);
	    }

	    return votacoes;
	}
	
	
	
	
	public void addVotacao(Votacao votacao) {
		try {
			conn = DB.getConnection();
			stt = conn.prepareStatement("INSERT INTO votacao" + "(nome_votacao, data_inicio, data_fim, tipo_votacao)" + "VALUES" + "(?, ?, ?, ?)");

			
			// timestamp é utilizado p colunas "datatime" no sql;
			Timestamp dataInicioTimestamp = new Timestamp(votacao.getData_inicio().getTime());
	        Timestamp dataFimTimestamp = new Timestamp(votacao.getData_fim().getTime());
	        
	        stt.setString(1, votacao.getNome_votacao());
			stt.setTimestamp(2, dataInicioTimestamp);
			stt.setTimestamp(3, dataFimTimestamp);
			stt.setString(4, votacao.getTipo_Votacao());
			stt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt);
		//	DB.closeConnection();
		}

	}
	
	public void updateVotacao(int idVotacao, String nome_votacao, java.util.Date dataInicio, java.util.Date dataFim, String tipoVotacao) {
		try {
			conn = DB.getConnection();
			stt1 = conn.prepareStatement("Update votacao " + "SET nome_votacao = ?, data_inicio = ?, data_fim = ?, tipo_votacao = ?" + "WHERE " + "id_votacao = ?");

			Timestamp dataInicioTimestamp = new Timestamp(dataInicio.getTime());
	        Timestamp dataFimTimestamp = new Timestamp(dataFim.getTime());
			
	        stt1.setString(1, nome_votacao);
	        stt1.setTimestamp(2, dataInicioTimestamp);
	        stt1.setTimestamp(3, dataFimTimestamp);
	        stt1.setString(4, tipoVotacao);
	        stt1.setInt(5, idVotacao);

			stt1.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt1);
		//	DB.closeConnection();
		}
	}
	
	public void deleteVotacao(int idVotacao) {
		try {
			conn = DB.getConnection();
			stt2 = conn.prepareStatement("DELETE FROM votacao " + "WHERE " + "id_votacao = ?");

			stt2.setInt(1, idVotacao);

			stt2.executeUpdate();
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closestatement(stt2);
		//	DB.closeConnection();
		}
	}
	
	public Votacao searchVotacaoById(int idVotacao) {
		try {
			conn = DB.getConnection();
			stt = conn.prepareStatement("SELECT * FROM votacao " + "WHERE " + "id_votacao = ?");

			stt.setInt(1, idVotacao);
			
			rs = stt.executeQuery();
			if(rs.next()) {
				
			Votacao vtc = new Votacao(rs.getString("nome_votacao"), rs.getDate("data_inicio"), rs.getDate("data_fim"), rs.getString("tipo_votacao"));
			vtc.setId_votacao(rs.getInt("id_votacao"));
			
			return vtc;
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt);
		//	DB.closeConnection();
		}
		return null;				

	}
	
	public Votacao searchVotacaoByNome(String nomeVotacao) {
		try {
			conn = DB.getConnection();
			stt = conn.prepareStatement("SELECT * FROM votacao " + "WHERE " + "nome_votacao = ?");

			stt.setString(1, nomeVotacao);
			
			rs = stt.executeQuery();
			if(rs.next()) {
				
			Votacao vtc = new Votacao(rs.getString("nome_votacao"), rs.getDate("data_inicio"), rs.getDate("data_fim"), rs.getString("tipo_votacao"));
			vtc.setId_votacao(rs.getInt("id_votacao"));
			
			return vtc;
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt);
		//	DB.closeConnection();
		}
		return null;	

	}
	
	public java.util.Date buscaDataInicio(int idVotacao) {
		try {
			conn = DB.getConnection();
			stt = conn.prepareStatement("SELECT * FROM votacao " + "WHERE " + "id_votacao = ?");

			stt.setInt(1, idVotacao);
			
			rs = stt.executeQuery();
			if(rs.next()) {
				
			Votacao vtc = new Votacao(rs.getString("nome_votacao"), rs.getDate("data_inicio"), rs.getDate("data_fim"), rs.getString("tipo_votacao"));
			vtc.setId_votacao(rs.getInt("id_votacao"));
			
			return vtc.getData_inicio();
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt);
		//	DB.closeConnection();
		}
		return null;

	}
	
	public java.util.Date buscaDataFim(int idVotacao) {
		try {
			conn = DB.getConnection();
			stt = conn.prepareStatement("SELECT * FROM votacao " + "WHERE " + "id_votacao = ?");

			stt.setInt(1, idVotacao);
			
			rs = stt.executeQuery();
			if(rs.next()) {
				
			Votacao vtc = new Votacao(rs.getString("nome_votacao"), rs.getDate("data_inicio"), rs.getDate("data_fim"), rs.getString("tipo_votacao"));
			vtc.setId_votacao(rs.getInt("id_votacao"));
			
			return vtc.getData_fim();
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt);
		//	DB.closeConnection();
		}
		return null;

	}
	
	public ResultSet addIdVotacaoCandidatosCombobox() {
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM votacao where tipo_votacao = \"candidatos\"");
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
	
	public ResultSet addIdVotacaoPropostasCombobox() {
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM votacao where tipo_votacao = \"propostas\"");
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
	
}

