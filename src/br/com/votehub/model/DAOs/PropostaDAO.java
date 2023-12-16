package br.com.votehub.model.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.votehub.model.vo.Proposta;
import br.com.votehub.model.vo.Votante;

public class PropostaDAO {
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement stt = null;
	PreparedStatement stt1 = null;
	PreparedStatement stt2 = null;

	public List<Proposta> obterTodasPropostas() {
	    List<Proposta> propostas = new ArrayList<>();

	    try {
	        conn = DB.getConnection();
	        stt = conn.prepareStatement("SELECT * FROM proposta");

	        rs = stt.executeQuery();

	        while (rs.next()) {
	            int id_proposta = rs.getInt("id_proposta");
	            String titulo = rs.getString("titulo");
	            String descricao = rs.getString("descricao");
	            int id_votacao = rs.getInt("id_votacao");

	            Proposta proposta = new Proposta(titulo, descricao, id_votacao);
	            proposta.setId_Proposta(id_proposta);

	            propostas.add(proposta);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DB.closeResultSet(rs);
	        DB.closestatement(stt);
	    }

	    return propostas;
	}

	public void addPropostas(Proposta p) {
		try {
			conn = DB.getConnection();
			stt = conn.prepareStatement(
					"INSERT INTO proposta" + "(titulo , descricao, id_votacao)" + "VALUES" + "(?, ?, ?)");

			stt.setString(1, p.getTitulo());
			stt.setString(2, p.getDescricao());
			stt.setInt(3, p.getId_votacao());

			stt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt);
			// DB.closeConnection();
		}

	}

	public void updatePropostas(int id_Proposta, String titulo, String descricao, int id_votacao) {
		try {
			conn = DB.getConnection();
			stt1 = conn.prepareStatement("Update proposta " + "SET titulo = ?, descricao = ?, id_votacao = ? " + "WHERE"
					+ " id_proposta = ?");

			stt1.setString(1, titulo);
			stt1.setString(2, descricao);
			stt1.setInt(3, id_votacao);
			stt1.setInt(4, id_Proposta);

			stt1.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt1);
			// DB.closeConnection();
		}
	}
	
	public void updatePropostas(int id_Proposta, String titulo, String descricao) {
		try {
			conn = DB.getConnection();
			stt1 = conn.prepareStatement("Update proposta " + "SET titulo = ?, descricao = ?  " + "WHERE"
					+ " id_proposta = ?");

			stt1.setString(1, titulo);
			stt1.setString(2, descricao);
			stt1.setInt(3, id_Proposta);

			stt1.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt1);
			// DB.closeConnection();
		}
	}

	public void deletePropostas(int id_Proposta) {
		try {
			conn = DB.getConnection();
			stt2 = conn.prepareStatement("DELETE FROM proposta " + "WHERE " + "id_proposta = ?");

			stt2.setInt(1, id_Proposta);

			stt2.executeUpdate();
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closestatement(stt2);
			// DB.closeConnection();
		}
	}

	public Proposta searchPropostaById(int id_Proposta) {
		try {
			conn = DB.getConnection();
			stt = conn.prepareStatement("SELECT * FROM proposta " + "WHERE " + "id_proposta = ?");

			stt.setInt(1, id_Proposta);

			rs = stt.executeQuery();
			if (rs.next()) {

				Proposta p = new Proposta(rs.getString("titulo"), rs.getString("descricao"), rs.getInt("id_votacao"));

				return p;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt);
			// DB.closeConnection();
		}
		return null;

	}

	public ResultSet addTituloCombobox() {
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * \r\n" + "FROM proposta \r\n");
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
	
	public ResultSet addVotacaoCombobox() {
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * \r\n" + "FROM proposta \r\n");
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
//			DB.closeResultSet(rs);
//			DB.closestatement(st);
//			DB.closeConnection();
		}}

	public String obterDescricaoPorTitulo(String titulo) {
		try {
			conn = DB.getConnection();
			stt = conn.prepareStatement("SELECT descricao FROM proposta WHERE titulo = ?");
			stt.setString(1, titulo);
			rs = stt.executeQuery();

			if (rs.next()) {
				return rs.getString("descricao");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closestatement(stt);
			// DB.closeConnection();
		}
		return null;
	}
	public int obterIdPorTitulo(String titulo) {
		try {
			conn = DB.getConnection();
			stt = conn.prepareStatement("SELECT id_proposta FROM proposta WHERE titulo = ?");
			stt.setString(1, titulo);
			rs = stt.executeQuery();

			if (rs.next()) {
				return rs.getInt("id_proposta");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closestatement(stt);
			// DB.closeConnection();
		}
		return -1;

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

	public ResultSet obterTituloPorVotacao(int id_votacao) {
	    try {
	        conn = DB.getConnection();
	        stt = conn.prepareStatement("SELECT titulo FROM proposta WHERE id_votacao = ?");
	        stt.setInt(1, id_votacao);
	        return stt.executeQuery();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    }
	    return null;
	}
	
}


