package br.com.votehub.controller;

import br.com.votehub.model.DAOs.CandidatoDAO;
import br.com.votehub.model.DAOs.VotacaoVotanteDAO;
import br.com.votehub.model.DAOs.VotanteDAO;
import br.com.votehub.model.DAOs.VotoDAO;
import br.com.votehub.model.vo.VotacaoVotante;
import br.com.votehub.model.vo.Voto;

public class ControllerVoto {
	private VotoDAO votoRepository = new VotoDAO();
	private CandidatoDAO candidatoRepository = new CandidatoDAO();
	private VotanteDAO votanteRepository = new VotanteDAO();
	private VotacaoVotanteDAO votacaoVotanteRepository = new VotacaoVotanteDAO();

	public void registrarVoto(String numeroCandidato) throws BusinessException {
		//consertar esse metodo dps
		 validarRegistro(numeroCandidato);

		Voto vt = new Voto(numeroCandidato);
		votoRepository.addVoto(vt);

	}
	
	public void setCandidatoRepository(CandidatoDAO candidatoDAO) {
        this.candidatoRepository = candidatoDAO;
    }
	
	public void setVotoRepository(VotoDAO votoDAO) {
        this.votoRepository = votoDAO;
    }

	public void validarRegistro(String numeroCandidato) throws BusinessException {

		if (numeroCandidato == null || numeroCandidato.isBlank()) {
	        throw new BusinessException("O campo numero de candidato não pode estar vazio!");
	    }
		
	    if (numeroCandidato.length() > 100) {
	        throw new BusinessException("O numero de candidato não pode possuir mais do que 100 caracteres");
	    }

	    if (candidatoRepository.searchCandidatoById(numeroCandidato) == null) {
	        throw new BusinessException("Candidato inexistente, informe um numero valido!");
	    }


	}

	public void deletarVoto(int idVoto) throws BusinessException {

		validarExclusao(idVoto);

		votoRepository.deleteVoto(idVoto);

	}

	public void validarExclusao(int idVoto) throws BusinessException {

		if (votoRepository.searchVotoById(idVoto) == null) {

			throw new BusinessException("Voto não encontrado!");

		}
	}

	public void consultarVoto(int idVoto) {

		votoRepository.searchVotoById(idVoto);

	}

	public void validarConsulta(int idVoto) throws BusinessException {

		if (votoRepository.searchVotoById(idVoto) == null) {

			throw new BusinessException("Voto não encontrado, indorme um identificador valido!");

		}
	}
	
//	public void checarAptidao(int idVotacao, int idVotante) {
//		
//	}
	public void ApurarDiretor(int idVoto) throws BusinessException {
		votoRepository.apurarVotosDiretor();
		
		}
	
	public void ApurarReitor(int idVoto) throws BusinessException {
		votoRepository.apurarVotosReitor();

		}
	}
	

