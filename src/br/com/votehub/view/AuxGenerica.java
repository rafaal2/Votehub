package br.com.votehub.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerVotacaoVotante;
import br.com.votehub.controller.ControllerVoto;
import br.com.votehub.model.DAOs.DB;
import br.com.votehub.model.vo.Votante;

public class AuxGenerica {

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	private static int idVotacao;
	private static Votante idVotante;
	private static String tela1;
	private static String tela2;
	private static String tela3;
	private static String numeroCandidatoTela1, nomeCandidatoTela1, caminhoImagem1;
	private static String numeroCandidatoTela2, nomeCandidatoTela2, caminhoImagem2;
	private static String numeroCandidatoTela3, nomeCandidatoTela3, caminhoImagem3;

	public void obterCargos(int id) {
		try {
			System.out.println(id);
			conn = DB.getConnection();
			idVotacao = id;
			String query = "SELECT DISTINCT cargo \r\n FROM candidato \r\n WHERE id_votacao = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				String temp = rs.getString("cargo");
				if ("da".equals(temp)) {
					tela1 = temp;
				} else if ("diretor".equals(temp)) {
					tela2 = temp;
				} else if ("reitor".equals(temp)) {
					tela3 = temp;
				}
			}
			sequenciaTelas();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void sequenciaTelas() {
		if ("da".equals(tela1)) {
			SwingUtilities.invokeLater(() -> new Generica("da", idVotacao));
			tela1 = null;
		} else if ("diretor".equals(tela2)) {
			SwingUtilities.invokeLater(() -> new Generica("diretor", idVotacao));
			tela2 = null;
		} else if ("reitor".equals(tela3)) {
			SwingUtilities.invokeLater(() -> new Generica("reitor", idVotacao));
			tela3 = null;
		} else {
			SwingUtilities.invokeLater(ConfirmarVoto::new);
		}
	}

//	public void computarVotos() {
//		Votante vtt = getIdVotante();
//		ControllerVotacaoVotante cvv = new ControllerVotacaoVotante();
//		try {
//			cvv.registrarVotacaoVotante(1, vtt.getId_votante());
//		} catch (BusinessException error) {
//			JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
//			return;
//		}
//		try {
//			ControllerVoto contVoto = new ControllerVoto();
//			contVoto.registrarVoto(numerocandidato);
//		} catch (BusinessException error) {
//			JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
//		}
//			JOptionPane.showMessageDialog(null, "Voto cadastrado com sucesso!");
//	}
	

	public static void limpar() {
		tela1 = null;
		tela2 = null;
		tela3 = null;
	}

	public static String getNumeroCandidatoTela1() {
		return numeroCandidatoTela1;
	}

	public static void setNumeroCandidatoTela1(String numeroCandidatoTela1) {
		AuxGenerica.numeroCandidatoTela1 = numeroCandidatoTela1;
	}

	public static String getNomeCandidatoTela1() {
		return nomeCandidatoTela1;
	}

	public static void setNomeCandidatoTela1(String nomeCandidatoTela1) {
		AuxGenerica.nomeCandidatoTela1 = nomeCandidatoTela1;
	}

	public static String getCaminhoImagem1() {
		return caminhoImagem1;
	}

	public static void setCaminhoImagem1(String caminhoImagem1) {
		AuxGenerica.caminhoImagem1 = caminhoImagem1;
	}

	public static String getNumeroCandidatoTela2() {
		return numeroCandidatoTela2;
	}

	public static void setNumeroCandidatoTela2(String numeroCandidatoTela2) {
		AuxGenerica.numeroCandidatoTela2 = numeroCandidatoTela2;
	}

	public static String getNomeCandidatoTela2() {
		return nomeCandidatoTela2;
	}

	public static void setNomeCandidatoTela2(String nomeCandidatoTela2) {
		AuxGenerica.nomeCandidatoTela2 = nomeCandidatoTela2;
	}

	public static String getCaminhoImagem2() {
		return caminhoImagem2;
	}

	public static void setCaminhoImagem2(String caminhoImagem2) {
		AuxGenerica.caminhoImagem2 = caminhoImagem2;
	}

	public static String getNumeroCandidatoTela3() {
		return numeroCandidatoTela3;
	}

	public static void setNumeroCandidatoTela3(String numeroCandidatoTela3) {
		AuxGenerica.numeroCandidatoTela3 = numeroCandidatoTela3;
	}

	public static String getNomeCandidatoTela3() {
		return nomeCandidatoTela3;
	}

	public static void setNomeCandidatoTela3(String nomeCandidatoTela3) {
		AuxGenerica.nomeCandidatoTela3 = nomeCandidatoTela3;
	}

	public static String getCaminhoImagem3() {
		return caminhoImagem3;
	}

	public static void setCaminhoImagem3(String caminhoImagem3) {
		AuxGenerica.caminhoImagem3 = caminhoImagem3;
	}

	public static Votante getIdVotante() {
		return idVotante;
	}

	public static void setIdVotante(Votante idVotante) {
		AuxGenerica.idVotante = idVotante;
	}

}
