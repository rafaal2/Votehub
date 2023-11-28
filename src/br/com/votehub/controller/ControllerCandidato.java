package br.com.votehub.controller;

import br.com.votehub.model.DAOs.AdmDAO;
import br.com.votehub.model.DAOs.CandidatoDAO;
import br.com.votehub.model.DAOs.DB;
import br.com.votehub.model.vo.Candidato;
import java.sql.*;

public class ControllerCandidato {
	private CandidatoDAO candidatoRepository = new CandidatoDAO();

	public void registrarCandidato(String numeroCandidato, String nome, String cargo, int id_votacao)
			throws BusinessException {
		validarRegistro(numeroCandidato, nome, cargo);

		Candidato cd = new Candidato(numeroCandidato, nome, cargo, id_votacao);
		candidatoRepository.addCandidato(cd);
	}

	public void validarRegistro(String numeroCandidato, String nome, String cargo) throws BusinessException {
		try {
			candidatoRepository.verificarSeNumeroExiste(numeroCandidato);
		} catch (SQLException e) {
			throw new BusinessException("Número do candidato já está em uso!");
		}

		if (numeroCandidato.isBlank() || nome.isBlank() || cargo.isBlank()) {
			throw new BusinessException("Todos os campos devem estar preenchindos!");
		}

		if (numeroCandidato.length() > 100 || cargo.length() > 100) {
			throw new BusinessException("Os valores numero e cargo informados devem possuir limite de 100 caracteres");
		}

		if (nome.length() > 200) {
			throw new BusinessException("O nome inserido deve possuir limite de 200 caracteres");
		}

	}

	public void deletarCandidato(String numeroCandidato) throws BusinessException {
		validarExclusao(numeroCandidato);

		candidatoRepository.deleteCandidato(numeroCandidato);
	}

	public void validarExclusao(String numeroCandidato) throws BusinessException {
		if (candidatoRepository.searchCandidatoById(numeroCandidato) == null) {
			throw new BusinessException("Candidato referido não encontrado!");
		}
	}

	public void atualizarCandidato(String numeroCandidato, String nome, String cargo, int id_votacao)
			throws BusinessException {
		validarAtualizacao(numeroCandidato, nome, cargo);

		String nomeC;
		String cargoC;
		int id_votacaoC = 0;

		Candidato cddt = candidatoRepository.searchCandidatoById(numeroCandidato);

		if (nome.isBlank()) {
			nomeC = cddt.getNome();
		} else {
			nomeC = nome;
		}

		if (cargo.isBlank()) {
			cargoC = cddt.getCargo();
		} else {
			cargoC = cargo;
		}

		candidatoRepository.updateCandidato(numeroCandidato, nomeC, cargoC, id_votacaoC);

	}

	public void validarAtualizacao(String numeroCandidato, String nome, String cargo) throws BusinessException {
		if (candidatoRepository.searchCandidatoById(numeroCandidato) == null) {
			throw new BusinessException("Candidato referido não encontrado");

		}

		if (numeroCandidato.length() > 100 || cargo.length() > 100) {
			throw new BusinessException("Os valores numero e cargo informados devem possuir limite de 100 caracteres");
		}

		if (nome.length() > 200) {
			throw new BusinessException("O nome inserido deve possuir limite de 200 caracteres!");
		}

	}

	// deve ser alterado o quanto antes retornando uma lista de candidatos que possa
	// ser exibida na interface grafica
	public void exibirCandidato() {

		candidatoRepository.mostrarCandidatos();

	}
	
	public ResultSet exibirReitor() {

		return candidatoRepository.addCandidatosReitorCombobox();

	}
	
	public ResultSet exibirDiretor() {

		return candidatoRepository.addCandidatosDiretorCombobox();

	}

}