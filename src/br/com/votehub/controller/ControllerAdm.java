package br.com.votehub.controller;

import br.com.votehub.model.DAOs.AdmDAO;
import br.com.votehub.model.vo.Adm;

public class ControllerAdm {
	private AdmDAO AdmRepository = new AdmDAO();
	
	
	public void registrarAdm(int id_adm, String login, String senha)
			throws BusinessException {
		this.validarRegistro(id_adm, login, senha);

		Adm a = new Adm(id_adm, login, senha);
		AdmRepository.addAdm(a);
	}
	public void validarRegistro(int id_adm, String login, String senha) throws BusinessException {
		if(login.isBlank() || senha.isBlank()) {			
			throw new BusinessException("Todos os campos devem estar preenchindos!");			
		}
		
		if(login.length() > 100 || senha.length() > 100) {
			throw new BusinessException("Os valores login e senha informados devem possuir limite de 100 caracteres");
		}
		
		if(login.length() > 200) {
			throw new BusinessException("O login deve possuir limite de 200 caracteres!");
		}
	}
	public void deletarAdm(int id_adm) throws BusinessException {
		AdmRepository.deleteAdm(id_adm);
	}
	//public void validarExclusao(int id_adm) throws BusinessException {
		//if (AdmRepository.searchVotanteById(id_adm) == null) {
			//throw new BusinessException("Adm referido não encontrado!");}
	public void atualizarAdm(int id_adm, String login, String senha)
			throws BusinessException {
		validarAtualizacao(id_adm, login, senha);

		AdmRepository.updateAdm(id_adm, login, senha);
	}
	public void validarAtualizacao(int id_adm, String login, String senha)
			throws BusinessException {
		//if (AdmRepository.searchVotanteById(id_votante) == null) {
			//throw new BusinessException("Votante referido não encontrado");
		//}

		if (id_adm != 0 || login.isBlank() || senha.isBlank()) {
			throw new BusinessException("Todos os campos devem estar preenchidos!");
		}

	}
	
	
	
	
	
	
	
	

}
