package br.com.votehub.model.vo;

public class Votante {

	private int id_votante;
	private String matricula;
	private String nome;
	private String senha;
	private String ocupação;

	public Votante(int id_votante, String matricula, String nome, String senha, String ocupação) {
		this.id_votante = id_votante;
		this.matricula = matricula;
		this.nome = nome;
		this.senha = senha;
		this.ocupação = ocupação;
	}

	public int getId_votante() {
		return id_votante;
	}

	public void setId_votante(int id) {
		this.id_votante = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getOcupação() {
		return ocupação;
	}

	public void setOcupação(String ocupação) {
		this.ocupação = ocupação;
	}

	public String toString() {
		return "[" + this.id_votante + ", " + this.matricula + ", " + this.nome + ", " + this.senha + ", " + this.ocupação + "]";
	}
}
