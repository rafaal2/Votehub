package br.com.votehub.controller;

import java.sql.SQLException;

import br.com.votehub.model.DAOs.PropostaDAO;
import br.com.votehub.model.vo.Proposta;

public class ControllerProposta {

	private PropostaDAO propostaRepository = new PropostaDAO();

	public void registrarVotante(String titulo, String resposta, int id_votacao)
			throws BusinessException, SQLException {
		this.validarRegistro(titulo, resposta, id_votacao);

		Proposta p = new Proposta(titulo, resposta, id_votacao);
		propostaRepository.addPropostas(p);

	}

	public void validarRegistro(String titulo, String resposta, int id_votacao) throws BusinessException, SQLException {

		if (titulo.isBlank()) {
			throw new BusinessException("o titulo deve ser preenchido.");
		}

		if (titulo.length() > 200) {
			throw new BusinessException("A proposta não pode exceder 300 caracteres.");
		}

		if (resposta.length() > 200) {
			throw new BusinessException("A senha deve ter no maximo 200 caracteres");
		}

		if (resposta.isBlank()) {
			throw new BusinessException("O nome deve ser preenchido.");
		}

//		if (id_votacao.isBlank()) {
//			throw new BusinessException("A senha deve ser preenchida.");
//		}
		
//		if (verificarSePropostaExiste(titulo)) {
//		throw new SQLException("A matrícula existente.");
//	}

	}

	public void deletarProposta(int id_Proposta) throws BusinessException {
		validarExclusao(id_Proposta);

		propostaRepository.deletePropostas(id_Proposta);
	}

	public void validarExclusao(int id_Proposta) throws BusinessException {
		if (propostaRepository.searchPropostaById(id_Proposta) == null) {
			throw new BusinessException("Proposta referida não encontrada!");
		}
	}

	public void atualizarProposta(int id_Proposta, String titulo, String resposta, int id_votacao) throws BusinessException {
		validarAtualizacao(id_Proposta, titulo, resposta, id_votacao);

		propostaRepository.updatePropostas(id_Proposta, titulo, resposta, id_votacao);
	}

	public void validarAtualizacao(int id_Proposta, String titulo, String resposta, int id_votacao) throws BusinessException {
		if (propostaRepository.searchPropostaById(id_Proposta) == null) {
			throw new BusinessException("Proposta referida não encontrada");
		}

		if (titulo.isBlank()) {
			throw new BusinessException("O titulo deve ser preenchido.");
		}

		if (resposta.isBlank()) {
			throw new BusinessException("A resposta deve ser preenchida.");
		}

	}

}
