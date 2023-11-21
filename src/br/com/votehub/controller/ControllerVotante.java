package br.com.votehub.controller;

import br.com.votehub.model.vo.Votante;
import br.com.votehub.model.DAOs.*;

public class ControllerVotante {

	private VotanteDAO votanteRepository = new VotanteDAO();

	// resolver questão do id_votante, que é auto increment no banco de dados.
	public void registrarVotante(int id_votante, String matricula, String nome, String senha, String ocupacao)
			throws BusinessException {
		this.validarRegistro(id_votante, matricula, nome, senha, ocupacao);

		Votante vt = new Votante(0, matricula, nome, senha, ocupacao);
		votanteRepository.addVotante(vt);
	}

	public void validarRegistro(int id_votante, String matricula, String nome, String senha, String ocupacao)
			throws BusinessException {

		if (id_votante != 0 || matricula.isBlank() || nome.isBlank() || senha.isBlank() || ocupacao.isBlank()) {
			throw new BusinessException("Todos os campos devem estar preenchidos!");
		}
	}

	public void deletarVotante(int id_votante) throws BusinessException {
//		validarExclusao(id_votante);

		votanteRepository.deleteVotante(id_votante);
	}

//	 criar o método searchVotanteById() na classe VotanteDAO
	public void validarExclusao(int id_votante) throws BusinessException {
		if (votanteRepository.searchVotanteById(id_votante) == null) {
			throw new BusinessException("Votante referido não encontrado!");
		}
	}

	public void atualizarVotante(int id_votante, String matricula, String nome, String senha, String ocupacao)
			throws BusinessException {
		validarAtualizacao(id_votante, matricula, nome, senha, ocupacao);

		votanteRepository.updateVotante(id_votante, matricula, nome, senha, ocupacao);
	}

	public void validarAtualizacao(int id_votante, String matricula, String nome, String senha, String ocupacao)
			throws BusinessException {
		if (votanteRepository.searchVotanteById(id_votante) == null) {
			throw new BusinessException("Votante referido não encontrado");
		}

		if (id_votante != 0 || matricula.isBlank() || nome.isBlank() || senha.isBlank() || ocupacao.isBlank()) {
			throw new BusinessException("Todos os campos devem estar preenchidos!");
		}

	}
}
