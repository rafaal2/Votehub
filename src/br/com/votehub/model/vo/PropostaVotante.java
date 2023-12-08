package br.com.votehub.model.vo;

public class PropostaVotante {
	private int idPropostaVotante;
	private int idProposta;
	private int idVotante;
	
	public PropostaVotante(int idVotante, int idProposta) {
		super();
		this.idVotante = idVotante;
		this.idProposta = idProposta;
	}

	public int getIdPropostaVotante() {
		return idPropostaVotante;
	}

	public void setIdPropostaVotante(int idPropostaVotante) {
		this.idPropostaVotante = idPropostaVotante;
	}

	public int getIdVotante() {
		return idVotante;
	}

	public void setIdVotante(int idVotante) {
		this.idVotante = idVotante;
	}

	public int getIdProposta() {
		return idProposta;
	}

	public void setIdProposta(int idProposta) {
		this.idProposta = idProposta;
	}
	
//	public String toString() {
//		
//		
//	}

}
