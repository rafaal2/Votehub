package br.com.votehub.model.vo;

public class Voto {

	private int id_voto;
	private String numero_candidato;
	private int id_votante;
	
	
	public Voto(String numero_candidato, int id_votante) {
		this.id_voto = id_voto;
		this.numero_candidato = numero_candidato;
		this.id_votante = id_votante;
	}

	public int getId_voto() {
		return id_voto;
	}

	public void setId_voto(int id_voto) {
		this.id_voto = id_voto;
	}

	public String getNumero_candidato() {
		return numero_candidato;
	}

	public void setNumero_candidato(String numero_candidato) {
		this.numero_candidato = numero_candidato;
	}

	public int getId_votante() {
		return id_votante;
	}

	public void setId_votante(int id_votante) {
		this.id_votante = id_votante;
	}

}
