package br.com.votehub.model.vo;

public class VotacaoVotante {
	private int id_votacaoVotante;
	private int id_votacao;
	private int id_votante;

	public int getId_votacaoVotante() {
		return id_votacaoVotante;
	}

	public void setId_votacaoVotante(int id_votacaoVotante) {
		this.id_votacaoVotante = id_votacaoVotante;
	}

	public int getId_votacao() {
		return id_votacao;
	}

	public void setId_votacao(int id_votacao) {
		this.id_votacao = id_votacao;
	}

	public int getId_votante() {
		return id_votante;
	}

	public void setId_votante(int id_votante) {
		this.id_votante = id_votante;
	}

	public VotacaoVotante(int id_votacao, int id_votante) {
		super();
		this.id_votacao = id_votacao;
		this.id_votante = id_votante;
	}
}
