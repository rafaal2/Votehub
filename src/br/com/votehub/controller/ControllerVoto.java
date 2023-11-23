package br.com.votehub.controller;

import br.com.votehub.model.DAOs.CandidatoDAO;
import br.com.votehub.model.DAOs.VotanteDAO;
import br.com.votehub.model.DAOs.VotoDAO;
import br.com.votehub.model.vo.Voto;

public class ControllerVoto {
	private VotoDAO votoRepository = new VotoDAO();
	private CandidatoDAO candidatoRepository = new CandidatoDAO();
	private VotanteDAO votanteRepository = new VotanteDAO();
	
	public void registrarVoto(int idVoto, String numeroCandidato, int idVotante) throws BusinessException {
		validarRegistro(idVoto, numeroCandidato, idVotante);
		
		Voto vt = new Voto(idVoto, numeroCandidato, idVotante);
		votoRepository.addVoto(vt);
		
	}
	
	public void validarRegistro(int idVoto, String numeroCandidato, int idVotante) throws BusinessException {
		if(votoRepository.searchVotoById(idVotante) != null) {
			
			throw new BusinessException("Eleitor já possui voto registrado!");
			
	} 
		if (candidatoRepository.searchCandidatoById(numeroCandidato) == null) {
			throw new BusinessException("Candidato inexistente, informe um numero valido!");
		}
		
		if(votanteRepository.searchVotanteById(idVotante) == null) {
			throw new BusinessException("Eleitor não identificado, insira as credenciais corretas!");
		}
		
	}
	
	public void deletarVoto(int idVoto) throws BusinessException {
		validarExclusao(idVoto);
		
		votoRepository.deleteVoto(idVoto);
		
	}
	
	
	public void validarExclusao(int idVoto) throws BusinessException {
		if(votoRepository.searchVotoById(idVoto) == null) {
			
			throw new BusinessException("Voto não encontrado!");
			
		}
	}
	
	public void consultarVoto(int idVoto) {
		
		votoRepository.searchVotoById(idVoto);
		
	}
	
	public void validarConsulta(int idVoto) throws BusinessException {
		if(votoRepository.searchVotoById(idVoto) == null) {
			
			throw new BusinessException("Voto não encontrado, indorme um identificador valido!");
			
		}
	}
}

