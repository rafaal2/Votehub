package br.com.votehub.model.vo;

public class Votante {

	private String nome;
	private String id;
	private String ocupacao;
	
	public Votante(String nome, String id, String ocupacao) {
		this.nome = nome;
		this.id = id;
		this.ocupacao = ocupacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
	}


	public String toString() {
		return "[" + this.nome + ", " + this.id + ", " + this.ocupacao + "]";
	}
	

	
	
}
