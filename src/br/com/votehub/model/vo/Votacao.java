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
	private String nome_votacao;
	private Date data_inicio;
	private Date data_fim;
	private String tipo_Votacao;
	private boolean status;

	public Votacao(String nome_votacao, Date data_inicio, Date data_fim, String tipoVotacao) {
		this.id_votacao = id_votacao;
		this.nome_votacao = nome_votacao;
		this.data_inicio = data_inicio;
		this.data_fim = data_fim;
		this.tipo_Votacao = tipoVotacao;
	}
	
	public Votacao(int id, String nome_votacao, Date data_inicio, Date data_fim, String tipoVotacao) {
		this.id_votacao = id;
		this.nome_votacao = nome_votacao;
		this.data_inicio = data_inicio;
		this.data_fim = data_fim;
		this.tipo_Votacao = tipoVotacao;
	}

	public int getId_votacao() {
		return id_votacao;
	}

	public void setId_votacao(int id_votacao) {
		this.id_votacao = id_votacao;
	}

	public String getNome_votacao() {
		return nome_votacao;
	}

	public void setNome_votacao(String nome_votacao) {
		this.nome_votacao = nome_votacao;
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
	
	public String getTipo_Votacao() {
		return tipo_Votacao;
	}

	public void setTipoVotacao(String tipo_Votacao) {
		this.tipo_Votacao = tipo_Votacao;
	}
	
//	public String toString() {
//		
//		return "ID: " +id_votacao+ "| Nome: " +nome_votacao+ "| Com inicio dia: " +data_inicio+ " e termino dia: " +data_fim+"|";
//		
//	}

}
