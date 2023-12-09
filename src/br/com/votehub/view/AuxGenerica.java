package br.com.votehub.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.SwingUtilities;

import br.com.votehub.model.DAOs.DB;

public class AuxGenerica {
	
	Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
	private static int idVotacao;
	private static String tela1;
	private static String tela2;
	private static String tela3;
	private String numeroCandidatoTela1, nomeCandidatoTela1, caminhoImagem1;
	private String numeroCandidatoTela2, nomeCandidatoTela2, caminhoImagem2;
	private String numeroCandidatoTela3, nomeCandidatoTela3, caminhoImagem3;


	public void obterCargos(int id) {
		try {
			System.out.println(id);
            conn = DB.getConnection();
            idVotacao = id;
            String query = "SELECT DISTINCT cargo \r\n FROM candidato \r\n WHERE id_votacao = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()) {
            	String temp = rs.getString("cargo");
            	if("da".equals(temp)) {
            		tela1 = temp;
            	} else if("diretor".equals(temp)) {
            		tela2 = temp;
            	} else if("reitor".equals(temp)) {
            		tela3 = temp;
            	}
            }
            sequenciaTelas();    
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void sequenciaTelas() {
		if("da".equals(tela1)) {
			SwingUtilities.invokeLater(() -> new Generica("da", idVotacao));
			tela1 = null;
		}else if("diretor".equals(tela2)) {
			SwingUtilities.invokeLater(() -> new Generica("diretor", idVotacao));
			tela2 = null;
		}else if("reitor".equals(tela3)) {
			SwingUtilities.invokeLater(() -> new Generica("reitor", idVotacao));
			tela3 = null;
		}else{
			SwingUtilities.invokeLater(ConfirmacaoVoto::new);
		}
	}
	
	public void computarVotos() {
		
		limpar();
	}
	
	public static void limpar() {
		tela1 = null;
		tela2 = null;
		tela3 = null;
	}
	
	
	
	
	public String getNumeroCandidatoTela1() {
		return numeroCandidatoTela1;
	}

	public void setNumeroCandidatoTela1(String numeroCandidatoTela1) {
		this.numeroCandidatoTela1 = numeroCandidatoTela1;
	}

	public String getNomeCandidatoTela1() {
		return nomeCandidatoTela1;
	}

	public void setNomeCandidatoTela1(String nomeCandidatoTela1) {
		this.nomeCandidatoTela1 = nomeCandidatoTela1;
	}

	public String getCaminhoImagem1() {
		return caminhoImagem1;
	}

	public void setCaminhoImagem1(String caminhoImagem1) {
		this.caminhoImagem1 = caminhoImagem1;
	}

	public String getNumeroCandidatoTela2() {
		return numeroCandidatoTela2;
	}

	public void setNumeroCandidatoTela2(String numeroCandidatoTela2) {
		this.numeroCandidatoTela2 = numeroCandidatoTela2;
	}

	public String getNomeCandidatoTela2() {
		return nomeCandidatoTela2;
	}

	public void setNomeCandidatoTela2(String nomeCandidatoTela2) {
		this.nomeCandidatoTela2 = nomeCandidatoTela2;
	}

	public String getCaminhoImagem2() {
		return caminhoImagem2;
	}

	public void setCaminhoImagem2(String caminhoImagem2) {
		this.caminhoImagem2 = caminhoImagem2;
	}

	public String getNumeroCandidatoTela3() {
		return numeroCandidatoTela3;
	}

	public void setNumeroCandidatoTela3(String numeroCandidatoTela3) {
		this.numeroCandidatoTela3 = numeroCandidatoTela3;
	}

	public String getNomeCandidatoTela3() {
		return nomeCandidatoTela3;
	}

	public void setNomeCandidatoTela3(String nomeCandidatoTela3) {
		this.nomeCandidatoTela3 = nomeCandidatoTela3;
	}

	public String getCaminhoImagem3() {
		return caminhoImagem3;
	}

	public void setCaminhoImagem3(String caminhoImagem3) {
		this.caminhoImagem3 = caminhoImagem3;
	}
	
	
}
