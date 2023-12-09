package br.com.votehub.controller;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.votehub.model.DAOs.AdmDAO;
import br.com.votehub.model.DAOs.DB;
import br.com.votehub.model.vo.Adm;
import br.com.votehub.model.vo.Votante;

public class ControllerAdm {
	private AdmDAO AdmRepository = new AdmDAO();

	public void registrarAdm(int id_adm, String login, String senha) throws BusinessException {
		this.validarRegistro(id_adm, login, senha);

		Adm a = new Adm(login, senha);
		AdmRepository.addAdm(a);
	}

	public void validarRegistro(int id_adm, String login, String senha) throws BusinessException {
//		if (AdmRepository.verificarSeIdExiste(id_adm)) {
//			throw new BusinessException ("ID informado já existe!");
//		}
		
		if (login.isBlank() || senha.isBlank()) {
			throw new BusinessException("Todos os campos devem estar preenchidos.");
		}

		if (senha.length() > 10) {
			throw new BusinessException("A senha informada deve possuir limite de 10 caracteres!");
		}

		if (login.length() > 10) {
			throw new BusinessException("O login deve possuir limite de 10 caracteres!");
		}
	}

	public void deletarAdm(int id_adm) throws BusinessException {
		AdmRepository.deleteAdm(id_adm);
	}

	// public void validarExclusao(int id_adm) throws BusinessException {
	// if (AdmRepository.searchVotanteById(id_adm) == null) {
	// throw new BusinessException("Adm referido não encontrado!");}
	public void atualizarAdm(int id_adm, String login, String senha) throws BusinessException {
		validarAtualizacao(id_adm, login, senha);

		AdmRepository.updateAdm(id_adm, login, senha);
	}

	public void validarAtualizacao(int id_adm, String login, String senha) throws BusinessException {

		if (id_adm != 0 || login.isBlank() || senha.isBlank()) {
			throw new BusinessException("Todos os campos devem estar preenchidos!");
		}

	}

	public void verificarloginadm(String loginDigitada) throws BusinessException, SQLException {
		AdmRepository.verificarloginadm(loginDigitada);
		Adm admin = AdmRepository.searchAdmByLogin(loginDigitada);
		if (admin == null) {
	        throw new BusinessException("login não encontrada");
	    }
	}

	public void verificarsenhaadm(String senhaDigitada) throws BusinessException, SQLException {
		AdmRepository.verificarsenhaadm(senhaDigitada);
	}

	public void validarVerificarloginadm(String logindigit, String senhadigit) throws BusinessException {
		if (logindigit.isBlank() || senhadigit.isBlank()) {
			throw new BusinessException("Todos os campos devem estar preenchidos!");
		}
		if (logindigit.length() > 100 || senhadigit.length() > 100) {
			throw new BusinessException("Os valores numero e cargo informados devem possuir limite de 100 caracteres");
		}

	}

}
