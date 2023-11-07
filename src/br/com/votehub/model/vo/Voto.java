package br.com.votehub.model.vo;

public class Voto {
	
	private int idVoto;
	private int idEleicao;
	private Votante votante;
	private Candidato candidato;
	//private Boolean validez; //alterado apenas quando o voto for anulado
	
	public Voto(int idVoto, int idEleicao, Votante votante, Candidato candidato) {
		this.idVoto = idVoto;
		this.idEleicao = idEleicao;
		this.votante = votante;
		this.candidato = candidato;
		
		//this.validez = true; //por padrão o voto é valido
	}
	
	public int getIdVoto() {
		return idVoto;
	}
	
	public int getIdEleicao() {
		return idEleicao;
	}
	
	//public Votante getVotante() { 
	//	return this.votante;
	//}
	
	public Candidato getCandidato() {
		return candidato;
	}
	
	//public Boolean ehValido() {
	//	return validez;
	//}
	
	//public void anularVoto() { //seria chamado a partir da ação do "btnNulo"
	//	this.validez = false;
	//}
	
}
