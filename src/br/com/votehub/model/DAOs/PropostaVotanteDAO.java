package br.com.votehub.model.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.votehub.model.vo.PropostaVotante;

public class PropostaVotanteDAO {
	
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement stt = null;
	PreparedStatement stt1 = null;
	PreparedStatement stt2 = null;	
	
	public void addpropostaVotante(PropostaVotante pv) {
		try {
			
			conn = DB.getConnection();
			stt = conn.prepareStatement("INSERT INTO propostavotante" + "(id_proposta, id_votante )" + "VALUES" + "(?, ?)");

			stt.setInt(1, pv.getIdProposta());
			stt.setInt(2, pv.getIdVotante());

			stt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			DB.closestatement(stt);
	//		DB.closeConnection();
			
		}
	}	
	
	public boolean verificarRespostaUnica(int idVotante, int idProposta) throws SQLException {	
		
		//testar em caso de erro
//		Connection conn = null;
//		ResultSet rs = null;
//		Statement st = null;
		
		try {
			
			conn = DB.getConnection();
			stt = conn.prepareStatement("SELECT id_votante FROM propostavotante " + "WHERE " + "id_proposta = ?");
			
			stt.setInt(1, idProposta);
			
			rs = stt.executeQuery();
			
			while (rs.next()) {
				
				//id(s)votantes do bd
				int votanteBD = rs.getInt("id_votante");
				String votanteBdString = Integer.toString(votanteBD);
				
				//id votante que tenta votar
				String idVotanteString = Integer.toString(idVotante);
				
				boolean check = votanteBdString.equals(idVotanteString);
				
				if (check == true) {
					
					return check;
					
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			DB.closeResultSet(rs);
		//	DB.closestatement(st);
			
		}
		
		return false;
	}	
	
	public PropostaVotante searchVotacaoVotanteById(int idPropostaVotante) {
		
		try {
			
			conn = DB.getConnection();
			stt = conn.prepareStatement("SELECT * FROM propostavotante " + "WHERE " + "id_propostaVotante = ?");

			stt.setInt(1, idPropostaVotante);
			
			rs = stt.executeQuery();
			
			if(rs.next()) {
				
			PropostaVotante pv = new PropostaVotante(rs.getInt("id_votante"), rs.getInt("id_proposta"));
			pv.setIdPropostaVotante(rs.getInt("id_propostaVotante"));
			
			return pv;
			
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			DB.closestatement(stt);
		//	DB.closeConnection();
			
		}
		
		return null;
			
	}	
	
	// vvv deverá sofrer alterações vvv
	
//	public void mostrarVotacaoVotante() {
//		try {
//			conn = DB.getConnection();
//			st = conn.createStatement();
//			rs = st.executeQuery("SELECT * \r\n" + "FROM propostaVotante \r\n");
//			while (rs.next()) {
//				System.out.println("votante: " + rs.getString("id_votante") + " proposta: " + rs.getString("id_proposta"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			DB.closeResultSet(rs);
//			DB.closestatement(st);
//	//		DB.closeConnection();
//		}
//	}
	
}
