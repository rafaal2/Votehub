package br.com.votehub.model.vo;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.sql.*;

public class Votacao {
	// '1000-01-01 00:00:00' formatação do date time
	private int id_votacao;
	private Date data_inicio;
	private Date data_fim;
	private boolean status;

	public Votacao(Date data_inicio, Date data_fim) {
		this.id_votacao = id_votacao;
		this.data_inicio = data_inicio;
		this.data_fim = data_fim;
	}

	public int getId_votacao() {
		return id_votacao;
	}

	public void setId_votacao(int id_votacao) {
		this.id_votacao = id_votacao;
	}

	public Date getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}

	public Date getData_fim() {
		return data_fim;
	}

	public void setData_fim(Date data_fim) {
		this.data_fim = data_fim;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	// private ArrayList<Votante> votantes;
	// private ArrayList<Candidato> candidatos;

	/*
	 * public Votação(int idEleicao, String data, String nome) { this.idEleicao =
	 * idEleicao; this.nome = nome; this.votantes = new ArrayList<>();
	 * this.candidatos = new ArrayList<>(); setData(data); }
	 * 
	 * public int getIdEleicao() { return idEleicao; }
	 * 
	 * public void setIdEleicao(int idEleicao) { this.idEleicao = idEleicao; }
	 * 
	 * public Date getData() { return data; }
	 * 
	 * public void setData(String dataStr) { SimpleDateFormat sdf = new
	 * SimpleDateFormat("dd/MM/yyyy");
	 * sdf.setTimeZone(TimeZone.getTimeZone("GMT-3")); try { Date data =
	 * sdf.parse(dataStr); this.data = data; } catch (ParseException e) {
	 * e.printStackTrace(); } }
	 * 
	 * public String getNome() { return nome; }
	 * 
	 * public void setNome(String nome) {
	 * 
	 * this.nome = nome; }
	 * 
	 * public void adicionarVotante(Votante votante) { votantes.add(votante); }
	 * 
	 * public void adicionarCandidato(Candidato candidato) {
	 * candidatos.add(candidato); }
	 * 
	 * public void removerVotante(Votante votante) { votantes.remove(votante); }
	 * 
	 * public void removerCandidato(Candidato candidato) {
	 * candidatos.remove(candidato); }
	 * 
	 * public ArrayList<Votante> getVotantes() { return votantes; }
	 * 
	 * public ArrayList<Candidato> getCandidatos() { return candidatos; }
	 * 
	 * public String toString() { SimpleDateFormat sdf = new
	 * SimpleDateFormat("dd/MM/yyyy"); String dataPadraoBr = sdf.format(data);
	 * return "Eleição: " + nome + " | Data: " + dataPadraoBr; }
	 */

}
