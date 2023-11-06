package br.com.votehub.model.vo;

public class Candidato {
	
	private String nome;
	private int idCandidato;
	private String cargo;
	
	public Candidato(String nome, int idCandidato, String cargo) {
		this.nome = nome;
		this.idCandidato = idCandidato;
		this.cargo = cargo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(int idCandidato) {
		this.idCandidato = idCandidato;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	public String toString() {
		return "Candidato: " + this.nome + " | id: " + idCandidato + " | " + cargo;
	}
	
	
	
	
}
