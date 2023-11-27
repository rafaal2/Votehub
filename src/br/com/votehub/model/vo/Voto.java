package br.com.votehub.model.vo;

public class Voto {

	private int id_voto;
	private String numero_candidato;
	
	
	public Voto(String numero_candidato) {
		this.id_voto = id_voto;
		this.numero_candidato = numero_candidato;
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


}
