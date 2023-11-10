package br.com.votehub.model.vo;

public class Voto {
	
	private int id;
	private int id_candidato;
	private int id_votante;
	
	public Voto(int id, int id_candidato ,int id_votante) {
		this.id = id;
		this.id_candidato = id_candidato;
		this.id_votante = id_votante;
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_candidato() {
		return id_candidato;
	}

	public void setId_candidato(int id_candidato) {
		this.id_candidato = id_candidato;
	}

	public int getId_votante() {
		return id_votante;
	}

	public void setId_votante(int id_votante) {
		this.id_votante = id_votante;
	}

	
}
