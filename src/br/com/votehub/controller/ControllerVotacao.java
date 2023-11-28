package br.com.votehub.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import br.com.votehub.model.DAOs.VotacaoDAO;
import br.com.votehub.model.vo.Votacao;

public class ControllerVotacao {
	
	private VotacaoDAO votacaoRepository = new VotacaoDAO();
	private Date now = new Date();
	
	public void registrarVotacao(String nomeVotacao, Date dataInicio, Date dataFim) throws BusinessException {
		validarRegistro(dataInicio, dataFim);
		
		Votacao vtc = new Votacao(nomeVotacao, dataInicio, dataFim);
		votacaoRepository.addVotacao(vtc);
	}
	
	public void validarRegistro(Date dataInicio, Date dataFim) throws BusinessException {
		
//		if(!validadorDataInicio(dataInicio)) {
//			throw new BusinessException("Insira uma data inicial existente");
//		}
//		
//		if(!validadorDataFim(dataFim)) {
//			throw new BusinessException("Insira uma data final existente");
//		}
		
		if(dataInicio.before(now)) {
			
			throw new BusinessException("A eleição deve iniciar em uma data atual");
		}
		
		if(dataFim.before(now)) {
			
			throw new BusinessException("A eleição deve ser finalizada em uma data atual ou futura");
		}
		
		if(!dataFim.after(dataInicio)) {
			
			throw new BusinessException("A data de termino deve ser após a data de inicio");
		}
		
	}
		
		
		public Boolean validadorDataInicio(Date dataInicio) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			
			String dataParaString = dateFormat.format(dataInicio);
			return dataIsValid(dataParaString);
		}
		
		public Boolean validadorDataFim(Date dataFim) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			
			String dataParaString = dateFormat.format(dataFim);
			return dataIsValid(dataParaString);
		}
		
		public Boolean dataIsValid(String data) {
		String formatacao = "dd/MM/yyyy"; 
		//se falhar tentar uuuu
		
		DateTimeFormatter dateFormatter = DateTimeFormatter
				.ofPattern(formatacao)
				.withResolverStyle(ResolverStyle.STRICT);
		
		try {
			LocalDate d = LocalDate.parse(data, dateFormatter);
			return true;
		} catch(DateTimeParseException e) {
			return false;
		}
		
	}
	
	public void atualizarVotacao(int idVotacao, String nome_votacao, Date dataInicio, Date dataFim) throws BusinessException {
		
		validarAtualizacao(idVotacao, nome_votacao, dataInicio, dataFim);
		//inserir condições para preenchimento de campos em branco
		
		votacaoRepository.updateVotacao(idVotacao, nome_votacao, dataInicio, dataFim);
	}
	
	public void validarAtualizacao(int idVotacao, String nome_votacao, Date dataInicio, Date dataFim) throws BusinessException {
		
		//alterar levando em considerações as condições do metodo a ser validado
		if(votacaoRepository.searchVotacaoById(idVotacao) == null) {
			
			throw new BusinessException("Votacao informada não encontrada!");
		}
		
		if(!validadorDataInicio(dataInicio)) {
			
			throw new BusinessException("Informe uma data inicial válida!");
		}
		
		if(!validadorDataFim(dataFim)) {
			
			throw new BusinessException("Informe uma data final válida");
		}
		
		if(dataInicio.before(now)) {
			
			throw new BusinessException("A nova data inicial deve ser atual ou futura");
		}
		
		if(dataFim.before(now)) {
			
			throw new BusinessException("A nova data de termino deve ser atual ou futura");
		}
		
		if(!dataFim.after(dataInicio)) {
			
			throw new BusinessException("A nova data de termino deve ser após a data de inicio");
		}
		
		
	}
	
	public void excluirVotacao(int idVotacao) {
		
		votacaoRepository.deleteVotacao(idVotacao);
	}
	
	public void validarExclusao(int idVotacao) throws BusinessException {
		if(votacaoRepository.searchVotacaoById(idVotacao) == null) {
			throw new BusinessException("Votação não encontrada no banco de dados!");
		}
	}
}
 			
