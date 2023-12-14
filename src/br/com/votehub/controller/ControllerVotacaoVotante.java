package br.com.votehub.controller;

import java.sql.SQLException;

import br.com.votehub.model.DAOs.VotacaoVotanteDAO;
import br.com.votehub.model.vo.VotacaoVotante;

public class ControllerVotacaoVotante {
		private VotacaoVotanteDAO VotacaoVotanteRepository = new VotacaoVotanteDAO();
		
		public void registrarVotacaoVotante (int id_votacao, int id_votante) throws BusinessException {
			
			VotacaoVotante vtvt = new VotacaoVotante(id_votacao, id_votante);
			VotacaoVotanteRepository.addVotacaoVotante(vtvt);
			
		}
		
		public void checarVotabilidade(int idVotante) throws SQLException, BusinessException {
			
			if(VotacaoVotanteRepository.verificarVotoUnico(idVotante)) {
				throw new BusinessException("Votante já possui voto registrado!");
			}
			
		}

		public void setVotacaoVotanteRepository(VotacaoVotanteDAO votacaoVotanteRepository) {
			this.VotacaoVotanteRepository = votacaoVotanteRepository;
			
		}
		
			
		
}


