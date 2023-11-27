package br.com.votehub.controller;

import br.com.votehub.model.DAOs.CandidatoDAO;
import br.com.votehub.model.DAOs.VotanteDAO;
import br.com.votehub.model.DAOs.VotoDAO;
import br.com.votehub.model.vo.Voto;

public class ControllerVoto {
	private VotoDAO votoRepository = new VotoDAO();
	private CandidatoDAO candidatoRepository = new CandidatoDAO();
	private VotanteDAO votanteRepository = new VotanteDAO();
	
	public void registrarVoto (String numeroCandidato) throws BusinessException {
		//validarRegistro( numeroCandidato);
		
		Voto vt = new Voto(numeroCandidato);
		votoRepository.addVoto(vt);
		
	}
	
//	public void validarRegistro( String numeroCandidato) throws BusinessException {
//		if(votoRepository.searchVotoById(idVotante) != null) {
//			
//			throw new BusinessException("Eleitor já possui voto registrado!");
//			
//	} 
//		if (candidatoRepository.searchCandidatoById(numeroCandidato) == null) {
//			throw new BusinessException("Candidato inexistente, informe um numero valido!");
//		}
//		
//		if(votanteRepository.searchVotanteById(idVotante) == null) {
//			throw new BusinessException("Eleitor não identificado, insira as credenciais corretas!");
//		}
//		
//	}
	
	public void deletarVoto(int id_Voto) throws BusinessException {
		validarExclusao(id_Voto);
		
		votoRepository.deleteVoto(id_Voto);
		
	}
	
	
	public void validarExclusao(int id_Voto) throws BusinessException {
		if(votoRepository.searchVotoById(id_Voto) == null) {
			
			throw new BusinessException("Voto não encontrado!");
			
		}
	}
	
	public void consultarVoto(int id_Voto) {
		
		votoRepository.searchVotoById(id_Voto);
		
	}
	
	public void validarConsulta(int id_Voto) throws BusinessException {
		if(votoRepository.searchVotoById(id_Voto) == null) {
			
			throw new BusinessException("Voto não encontrado, indorme um identificador valido!");
			
		}
	}
}

