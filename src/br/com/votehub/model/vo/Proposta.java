package br.com.votehub.model.vo;

public class Proposta {

	private int id_Proposta;
	private String titulo;
	private String resposta;
	private int id_votacao;

	public Proposta(String titulo, String resposta, int id_votacao) {
		this.titulo = titulo;
		this.resposta = resposta;
		this.id_votacao = id_votacao;
	}

	public int getId_Proposta() {
		return id_Proposta;
	}

	public void setId_Proposta(int id_Proposta) {
		this.id_Proposta = id_Proposta;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public int getId_votacao() {
		return id_votacao;
	}

	public void setId_votacao(int id_votacao) {
		this.id_votacao = id_votacao;
	}

}
