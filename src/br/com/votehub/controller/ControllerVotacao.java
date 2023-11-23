package br.com.votehub.controller;

import java.util.Date;

import br.com.votehub.model.DAOs.VotacaoDAO;
import br.com.votehub.model.vo.Votacao;

public class ControllerVotacao {
	
	private VotacaoDAO votacaoRepository = new VotacaoDAO();
	
	public void registrarVotacao(int idVotacao, Date dataInicio, Date dataFim) {
		
		Votacao vtc = new Votacao(idVotacao, dataInicio, dataFim);
		votacaoRepository.addVotacao(vtc);
	}
	
	public void validarRegistro(int idVotacao, Date dataInicio, Date dataFim) throws BusinessException {
		if(idVotacao <= 0) {
			throw new BusinessException("Inserir apenas numero de identificação positivo");
		}
		
		if(votacaoRepository.searchVotacaoById(idVotacao) != null) {
			throw new BusinessException("Essa votação já existe!");
		}
		
	}
	
	public void excluirVotacao(int idVotacao) {
		
		votacaoRepository.deleteVotacao(idVotacao);
	}
	
	public void validarExclusao(int idVotacao) throws BusinessException {
		if(votacaoRepository.searchVotacaoById(idVotacao) == null) {
			throw new BusinessException("Votação não encontrada no banco de dados!");
		}
	}
}
 			