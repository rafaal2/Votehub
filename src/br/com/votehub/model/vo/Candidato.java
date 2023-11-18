package br.com.votehub.model.vo;

public class Candidato {

	private String numero_candidato;
	private String nome;
	private String cargo;

	public Candidato(String numero_candidato, String nome, String cargo) {
		super();
		this.numero_candidato = numero_candidato;
		this.nome = nome;
		this.cargo = cargo;
	}

	public String getNumero_candidato() {
		return numero_candidato;
	}

	public void setNumero_candidato(String numero_candidatp) {
		this.numero_candidato = numero_candidatp;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String toString() {
		return "Candidato [numero_candidato=" + numero_candidato + ", nome=" + nome + ", cargo=" + cargo + "]";
	}

}
