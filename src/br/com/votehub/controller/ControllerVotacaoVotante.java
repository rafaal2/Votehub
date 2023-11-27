package br.com.votehub.controller;

import br.com.votehub.model.DAOs.VotacaoVotanteDAO;
import br.com.votehub.model.vo.VotacaoVotante;

public class ControllerVotacaoVotante {
		private VotacaoVotanteDAO VotacaoVotanteRepository = new VotacaoVotanteDAO();
		
		public void registrarVotacaoVotante (int id_votacao, int id_votante) throws BusinessException {
			//validarRegistro( numeroCandidato);
			
			VotacaoVotante vtvt = new VotacaoVotante(id_votacao, id_votante);
			VotacaoVotanteRepository.addVotacaoVotante(vtvt);
			
		}
}


