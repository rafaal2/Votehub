package br.com.votehub;

import br.com.votehub.model.vo.*;

public class Main {

	public static void main(String[] args) {
		
		Votante kaio = new Votante("Kaio", "2023ADSPL0250", "Estudante");
		System.out.println(kaio);
		Votante rafael = new Votante("Rafael", "2023ADSPL0251", "Estudante");
		System.out.println(kaio);
		Votante kelly = new Votante("Kelly", "2009RH001", "Servidora");
		
		Candidato george = new Candidato("George", 1, "Reitor");
		System.out.println(george);
		
		Eleicao eleicaoReitor = new Eleicao(1, "22/11/2023", "Reitor");
		System.out.println(eleicaoReitor);
		
		eleicaoReitor.adicionarCandidato(george);
		System.out.println(eleicaoReitor.getCandidatos());
		
		eleicaoReitor.adicionarVotante(kaio);
		eleicaoReitor.adicionarVotante(rafael);
		eleicaoReitor.adicionarVotante(kelly);
		System.out.println(eleicaoReitor.getVotantes());
	}

}
