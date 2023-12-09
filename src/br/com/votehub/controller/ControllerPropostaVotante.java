package br.com.votehub.controller;

import java.sql.SQLException;

import br.com.votehub.model.DAOs.PropostaVotanteDAO;
import br.com.votehub.model.vo.PropostaVotante;

public class ControllerPropostaVotante {

	private PropostaVotanteDAO propostaVotanteRepository = new PropostaVotanteDAO();
	
	public void adicionarPropostaVotante(int idVotante, int idProposta) {
		
		PropostaVotante pv = new PropostaVotante(idVotante, idProposta);		
		propostaVotanteRepository.addpropostaVotante(pv);
		
	}
	
	//metodo para lançar exception
	public void checarRespostaUnica (int idVotante, int idProposta) throws SQLException, BusinessException {
		
		if(propostaVotanteRepository.verificarRespostaUnica(idVotante, idProposta)) {
			
			throw new BusinessException("Votante já possui resposta registrada nessa proposta.");
			
		}
	}
			
	
}
