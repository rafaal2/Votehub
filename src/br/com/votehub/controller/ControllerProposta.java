package br.com.votehub.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.votehub.model.DAOs.PropostaDAO;
import br.com.votehub.model.vo.Proposta;
import br.com.votehub.model.vo.Votante;

public class ControllerProposta {

	private PropostaDAO propostaRepository = new PropostaDAO();

	public void registrarProposta(String titulo, String descricao, int id_votacao)
			throws BusinessException, SQLException {
		this.validarRegistro(titulo, descricao, id_votacao);

		Proposta p = new Proposta(titulo, descricao, id_votacao);
		propostaRepository.addPropostas(p);

	}

	public void validarRegistro(String titulo, String descricao, int id_votacao)
			throws BusinessException, SQLException {

		if (titulo.isBlank()) {
			throw new BusinessException("o titulo deve ser preenchido.");
		}

		if (titulo.length() > 150) {
			throw new BusinessException("o titulo não pode exceder 150 caracteres.");
		}

		if (descricao.isBlank()) {
			throw new BusinessException("A Descrição deve ser preenchida.");
		}

		if (descricao.length() > 2000) {
			throw new BusinessException("A Descrição deve ter no maximo 2000 caracteres.");
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

	public void atualizarProposta(int id_Proposta, String titulo, String descricao, int id_votacao)
			throws BusinessException {
		validarAtualizacao(id_Proposta, titulo, descricao, id_votacao);

		propostaRepository.updatePropostas(id_Proposta, titulo, descricao, id_votacao);
	}
	

	public void atualizarProposta(int id_Proposta, String titulo, String descricao)throws BusinessException {
		validarAtualizacao(id_Proposta, titulo, descricao);

		propostaRepository.updatePropostas(id_Proposta, titulo, descricao);
	}
	
	

	public void validarAtualizacao(int id_Proposta, String titulo, String descricao, int id_votacao)
			throws BusinessException {
		if (propostaRepository.searchPropostaById(id_Proposta) == null) {
			throw new BusinessException("Proposta referida não encontrada");
		}

		if (titulo.isBlank()) {
			throw new BusinessException("O titulo deve ser preenchido.");
		}

		if (descricao.isBlank()) {
			throw new BusinessException("A Descrição deve ser preenchida.");
		}

	}
	

	public void validarAtualizacao(int id_Proposta, String titulo, String descricao) throws BusinessException {
		if (propostaRepository.searchPropostaById(id_Proposta) == null) {
			throw new BusinessException("Proposta referida não encontrada");
		}

		if (titulo.isBlank()) {
			throw new BusinessException("O titulo deve ser preenchido.");
		}

		if (descricao.isBlank()) {
			throw new BusinessException("A Descrição deve ser preenchida.");
		}

	}

	public ResultSet exibirTitulo() {

		return propostaRepository.addTituloCombobox();

	}

	public String obterDescricaoPorTitulo(String titulo) {
		return propostaRepository.obterDescricaoPorTitulo(titulo);
	}

	public int obterIdPorTitulo(String titulo) throws SQLException {
		return propostaRepository.obterIdPorTitulo(titulo);
	}
	
	public Proposta buscarProposta(int id) {
		
		return propostaRepository.searchPropostaById(id);
		
	}
}
