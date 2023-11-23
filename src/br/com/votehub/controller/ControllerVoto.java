package br.com.votehub.controller;

import br.com.votehub.model.DAOs.VotoDAO;
import br.com.votehub.model.vo.Voto;

public class ControllerVoto {
	private VotoDAO votoRepository = new VotoDAO();
	
	public void registrarVoto(int idVoto, String numeroCandidato, int idVotante) throws BusinessException {
		validarRegistro(idVoto, numeroCandidato, idVotante);
		
		Voto vt = new Voto(idVoto, numeroCandidato, idVotante);
		votoRepository.addVoto(vt);
		
	}
	
	public void validarRegistro(int idVoto, String numeroCandidato, int idVotante) throws BusinessException {
		if(votoRepository.searchVotoById(idVotante) != null) {
			
			throw new BusinessException("Eleitor já possui voto registrado!");
			
	}
		
	}
	
	public void deletarVoto(int idVoto) throws BusinessException {
		validarExclusao(idVoto);
		
		votoRepository.deleteVoto(idVoto);
	}
	
	
	public void validarExclusao(int idVoto) throws BusinessException {
		if(votoRepository.searchVotoById(idVoto) == null) {
			throw new BusinessException("Candidato referido não encontrado!");
		}
	}
}

