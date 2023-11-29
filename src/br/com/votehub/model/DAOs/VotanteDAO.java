package br.com.votehub.model.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.jasypt.util.password.StrongPasswordEncryptor;

import br.com.votehub.controller.BusinessException;
import br.com.votehub.model.criptografia.Encriptador;
import br.com.votehub.model.vo.Votante;

public class VotanteDAO {
	private final static StrongPasswordEncryptor passHash = new StrongPasswordEncryptor();
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
				String encryptedNome = encrip.desencriptadorDeValores(rs.getString("nome"));
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

		StrongPasswordEncryptor senhacrip = new StrongPasswordEncryptor();
		try {
			conn = DB.getConnection();
			stt = conn.prepareStatement("INSERT INTO votante" + "( matricula, nome, senha)" + "VALUES" + "( ?, ?, ?)");

			stt.setString(1, v.getMatricula());
			stt.setString(2, v.getNome());
			stt.setString(3, senhacrip.encryptPassword(v.getSenha())); // stt.setString(4,
																		// Hash.gerarHash(v.getSenha()));

			stt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt);
		}

	}

	public void updateVotante(int idVotante, String novaMatricula, String novoNome, String novaSenha) {
		StrongPasswordEncryptor senhacrip = new StrongPasswordEncryptor();
		try {
			conn = DB.getConnection();
			stt1 = conn.prepareStatement("Update votante " + "SET matricula = ?, nome = ?, senha = ?"
					+ "WHERE" + "id_votante = ?");

			stt1.setString(1, novaMatricula);
			stt1.setString(2, novoNome);
			stt1.setString(3, senhacrip.encryptPassword(novaSenha)); // stt1.setString(4,
																			// Hash.gerarHash(novaSenha));
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
			stt = conn.prepareStatement("SELECT * FROM votante " + "WHERE " + "id_votante = ?");

			stt.setInt(1, id_votante);

			rs = stt.executeQuery();
			if (rs.next()) {

				Votante vtt = new Votante(rs.getString("matricula"), rs.getString("nome"), rs.getString("senha"));

				return vtt;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closestatement(stt);
			DB.closeConnection();
		}
		return null;

	}

	public static boolean verificarloginvot(String loginDigitada) throws SQLException, BusinessException {
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		boolean loginIncorreto = false;
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT matricula \r\n" + "FROM votante \r\n");
			while (rs.next()) {
				String matriculabd = rs.getString("matricula");
				boolean check = loginDigitada.equals(matriculabd);
				if (check == true) {
					return check;
				} else {
					loginIncorreto = true;
				}if (loginDigitada.isBlank()) {
					throw new BusinessException("o campo de login deve estar preenchido");
			}}
			if (loginIncorreto) {
				throw new BusinessException("login incorreto");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closestatement(st);

		}
		return false;
	}
	
	public static boolean verificarsenhavot( String senhaDigitada) throws SQLException, BusinessException {
		Connection conn = null;             
		ResultSet rs = null;
		Statement st= null;
		boolean senhaIncorreta = false;
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT senha \r\n" + "FROM votante \r\n");
			while(rs.next()) {
				String senhabd = rs.getString("senha");
				boolean check = passHash.checkPassword(senhaDigitada, senhabd);
				if (check) {
		            return true;
		        }if(senhaDigitada.isBlank()) {
					throw new BusinessException("o campo da senha deve estar preenchido");
				}if(senhaDigitada.length() < 7) {
					throw new BusinessException("a senha deve conter no minimo 8 caracteres");
				}else {
					senhaIncorreta = true;}
			        throw new BusinessException("Senha incorreta");	
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeResultSet(rs);
			DB.closestatement(st);
		}
		return false;
	}

}


