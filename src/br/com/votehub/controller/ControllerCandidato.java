package br.com.votehub.controller;

import br.com.votehub.model.DAOs.CandidatoDAO;
import br.com.votehub.model.DAOs.DB;
import br.com.votehub.model.vo.Candidato;

public class ControllerCandidato {
	
	private CandidatoDAO candidatoRepository = new CandidatoDAO();
	
	public void registraCandidato(String numeroCandidato, String nome, String cargo) throws BusinessException {
		validarRegistro(numeroCandidato, nome, cargo);
		
		Candidato cd = new Candidato(numeroCandidato, nome, cargo);
		candidatoRepository.addCandidato(cd);
	}
	
	
	public void deletarCandidato(String numeroCandidato) throws BusinessException {
		validarExclusao(numeroCandidato);
		
		candidatoRepository.deleteCandidato(numeroCandidato);
	}
	
	
	public void validarRegistro(String numeroCandidato, String nome, String cargo) throws BusinessException {
		if(numeroCandidato.isBlank() || nome.isBlank() || cargo.isBlank()) {			
			throw new BusinessException("Todos os campos devem estar preenchindos!");			
		}
	}
	
	public void validarExclusao(String numeroCandidato) throws BusinessException {
		if(candidatoRepository.searchCandidatoById(numeroCandidato) == null) {
			throw new BusinessException("Candidato referido não encontrado!");
		}
	}
	
	public void atualizarCandidato(String numeroCandidato, String nome, String cargo) throws BusinessException {
		validarAtualizacao(numeroCandidato, nome, cargo);
		
		candidatoRepository.updateCandidato(numeroCandidato, nome, cargo);
		//o metodo será melhorado para que informações em branco sejam substituidas pelo atributo atual do candidato
	}
	
	public void validarAtualizacao(String numeroCandidato, String nome, String cargo) throws BusinessException {
		if(candidatoRepository.searchCandidatoById(numeroCandidato) == null) {
			throw new BusinessException("Candidato referido não encontrado");
		}
		
		if(nome.isBlank() || cargo.isBlank()) {
			throw new BusinessException("Todos os campos devem estar preenchidos!");
			
			//o metodo será melhorado para que informações em branco sejam substituidas pelo atributo atual do candidato
		}
	}
	
	//deve ser alterado o quanto antes retornando uma lista de candidatos que possa ser exibida na interface grafica
	//public void exibirCandidato() { 
	//	candidatoRepository.mostrarCandidatos();
	//}
}

