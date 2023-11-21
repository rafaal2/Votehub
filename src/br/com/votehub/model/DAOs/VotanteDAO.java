package br.com.votehub.model.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.jasypt.util.password.StrongPasswordEncryptor;

import br.com.votehub.model.criptografia.Encriptador;
import br.com.votehub.model.criptografia.Hash;
import br.com.votehub.model.vo.Votante;

public class VotanteDAO {
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement stt = null;
	PreparedStatement stt1 = null;
	PreparedStatement stt2 = null;

	public void mostrarVotantes() {
		Encriptador encrip = new Encriptador();
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * \r\n" + "FROM votante \r\n");
			while (rs.next()) {
				String encryptedNome = encrip.encriptadorDeValores(rs.getString("nome"), "d");
				System.out.println("Votante: " + encryptedNome + " / " + rs.getString("ocupação"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closestatement(st);
			DB.closeConnection();
		}

	}

	public void mostrarsenha() {
		Encriptador encrip = new Encriptador();
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * \r\n" + "FROM votante \r\n");
			while (rs.next()) {
				String encryptedNome = encrip.encriptadorDeValores(rs.getString("senha"), "d");
				System.out.println("Votante: " + encryptedNome + " / " + rs.getString("ocupação"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closestatement(st);
			DB.closeConnection();
		}

	}

	public void addVotante(Votante v) {
		Encriptador encrip = new Encriptador();
		StrongPasswordEncryptor senhacrip = new StrongPasswordEncryptor();
		try {
			conn = DB.getConnection();
			stt = conn.prepareStatement("INSERT INTO votante" + "(id_votante, matricula, nome, senha, ocupação)"
					+ "VALUES" + "(?, ?, ?, ?, ?)");

			stt.setInt(1, v.getId_votante());
			stt.setString(2, encrip.encriptadorDeValores(v.getMatricula(), "C"));
			stt.setString(3, encrip.encriptadorDeValores(v.getNome(), "C"));
			stt.setString(4, encrip.encriptadorDeValores(v.getSenha(), "C")); // stt.setString(4,
																				// Hash.gerarHash(v.getSenha()));
			stt.setString(5, encrip.encriptadorDeValores(v.getOcupação(), "C"));

			stt.executeUpdate();
			System.out.println("Novo votante cadastrado");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt);
			DB.closeConnection();
		}

	}

	public void updateVotante(int idVotante, String novaMatricula, String novoNome, String novaSenha,
			String novaOcupacao) {
		Encriptador encrip = new Encriptador();
		try {
			conn = DB.getConnection();
			stt1 = conn.prepareStatement("Update votante " + "SET matricula = ?, nome = ?, senha = ?, ocupação = ?"
					+ "WHERE" + "id_votante = ?");

			stt1.setString(1, encrip.encriptadorDeValores(novaMatricula, "C"));
			stt1.setString(2, encrip.encriptadorDeValores(novoNome, "C"));
			stt1.setString(3, encrip.encriptadorDeValores(novaSenha, "C")); // stt1.setString(4,
																			// Hash.gerarHash(novaSenha));
			stt1.setString(4, encrip.encriptadorDeValores(novaOcupacao, "C"));
			stt1.setInt(5, idVotante);

			stt1.executeUpdate();
			System.out.println("Informações do votante " + idVotante + "atualizadas!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt1);
			DB.closeConnection();
		}
	}

	public void deleteVotante(int id_Votante) {
		try {
			conn = DB.getConnection();
			stt2 = conn.prepareStatement("DELETE FROM votante " + "WHERE " + "id_votante = ?");

			stt2.setInt(1, id_Votante);

			stt2.executeUpdate();
			System.out.println("Votante " + id_Votante + "deletado");
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closestatement(stt2);
			DB.closeConnection();
		}
	}

public Votante searchVotanteById(int id_votante) {
	try {
		conn = DB.getConnection();
		stt = conn.prepareStatement("SELECT * FROM candidato " + "WHERE " + "numero_candidato = ?");

		stt.setInt(1, id_votante);
		
		rs = stt.executeQuery();
		if(rs.next()) {
			
		Votante vt = new Votante(rs.getInt("id_votante"), rs.getString("matricula"), rs.getString("nome"), rs.getString("senha"), rs.getString("ocupação"));
		
		return vt;
		
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
