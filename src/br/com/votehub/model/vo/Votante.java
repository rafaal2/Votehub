package br.com.votehub.model.vo;

public class Votante {

	private String cpf;
	private String nome;
	private String ocupacao;
	
	public Votante(String cpf, String nome, String ocupacao) {
		this.cpf = cpf;
		this.nome = nome;
		this.ocupacao = ocupacao;
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
		return "[" + this.nome + ", " + this.cpf + ", " + this.ocupacao + "]";
	}
	

	
	
}
