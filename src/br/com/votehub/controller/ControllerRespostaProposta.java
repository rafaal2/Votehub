package br.com.votehub.controller;

import br.com.votehub.model.DAOs.RespostaPropostaDAO;
import br.com.votehub.model.vo.RespostaProposta;

public class ControllerRespostaProposta {
	private RespostaPropostaDAO respostapropostaRepository = new RespostaPropostaDAO();

	public void registrarRespostaProposta(String resposta, int id_proposta) throws BusinessException {
		//consertar esse metodo dps
		// validarRegistro(numeroCandidato);

		RespostaProposta pp = new RespostaProposta(resposta, id_proposta);
		respostapropostaRepository.addRespostaProposta(pp);
		

	}

//	public void validarRegistro(String numeroCandidato) throws BusinessException {
//
//		if (candidatoRepository.searchCandidatoById(numeroCandidato) == null) {
//
//			throw new BusinessException("Candidato inexistente, informe um numero valido!");
//		}
//
//		if (numeroCandidato.isBlank()) {
//			throw new BusinessException("O campo numero de candidato não pode estar vazio!");
//		}
//
//		if (numeroCandidato.length() > 100) {
//
//			throw new BusinessException("O numero de candidato não pode possuir mais do que 100 caracteres");
//		}
//
//	}
//
//	public void deletarVoto(int idVoto) throws BusinessException {
//
//		validarExclusao(idVoto);
//
//		votoRepository.deleteVoto(idVoto);
//
//	}
//
//	public void validarExclusao(int idVoto) throws BusinessException {
//
//		if (votoRepository.searchVotoById(idVoto) == null) {
//
//			throw new BusinessException("Voto não encontrado!");
//
//		}
//	}
//
//	public void consultarVoto(int idVoto) {
//
//		votoRepository.searchVotoById(idVoto);
//
//	}
//
//	public void validarConsulta(int idVoto) throws BusinessException {
//
//		if (votoRepository.searchVotoById(idVoto) == null) {
//
//			throw new BusinessException("Voto não encontrado, indorme um identificador valido!");
//
//		}
//	}

}
