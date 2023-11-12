package br.com.votehub.model.vo;

public class Votante {

	private String nome;
	private int id;
	private String cpf;
	private String ocupacao;

	public Votante(int id, String nome, String cpf, String ocupacao) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.ocupacao = ocupacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getcpf() {
		return cpf;
	}


	public void setcpf(String cpf) {
		this.cpf = cpf;
	}

	public String getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
	}

	public String toString() {
		return "[" + this.id  + ", " + this.nome + ", " + this.cpf + ", " + this.ocupacao + "]";
	}
}
