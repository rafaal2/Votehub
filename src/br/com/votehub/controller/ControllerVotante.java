package br.com.votehub.controller;

import br.com.votehub.model.vo.Votante;
import br.com.votehub.model.DAOs.*;

public class ControllerVotante {

	private VotanteDAO votanteRepository = new VotanteDAO();

//	public void registrarVotante(String matricula, String nome, String senha, String ocupacao) throws BusinessException {
//	    this.validarRegistro();
//	    
//	    Votante vt = new Votante(0, matricula, nome, senha, ocupacao);
//	    votanteRepository.addVotante(vt);
//	}

	public void validarRegistro(String matricula, String nome, String senha, String ocupacao) throws BusinessException {
	    if(matricula.isBlank() || nome.isBlank() || senha.isBlank() || ocupacao.isBlank()) {            
	        throw new BusinessException("Todos os campos devem estar preenchidos!");            
	    }
	}

}

