package br.com.votehub.model.vo;

public class Candidato {
	
	private String nome;
	private int id;
	private String cargo;
	
	public Candidato(String nome, int id, String cargo) {
		this.nome = nome;
		this.id = id;
		this.cargo = cargo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int idCandidato) {
		this.id = idCandidato;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	public String toString() {
		return "Candidato: " + this.nome + " | id: " + id + " | " + cargo;
	}
	
	
	
	
}
