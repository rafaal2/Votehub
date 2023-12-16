package br.com.votehub.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.votehub.model.DAOs.CandidatoDAO;
import br.com.votehub.model.DAOs.VotacaoDAO;
import br.com.votehub.model.vo.Candidato;
import br.com.votehub.model.vo.Votacao;
import br.com.votehub.model.vo.Votante;

public class ControllerVotacao {
	
	private VotacaoDAO votacaoRepository = new VotacaoDAO();
	private CandidatoDAO candidatoRepository = new CandidatoDAO();
	private Date now = new Date();
	
	public void registrarVotacao(String nomeVotacao, Date dataInicio, Date dataFim, String tipoVotacao) throws BusinessException {
		validarRegistro(nomeVotacao, dataInicio, dataFim);
		checarTipoVotacao(tipoVotacao);
		
		Votacao vtc = new Votacao(nomeVotacao, dataInicio, dataFim, tipoVotacao);
		votacaoRepository.addVotacao(vtc);
	}
	
	public void validarRegistro(String nomeVotacao, Date dataInicio, Date dataFim) throws BusinessException {	
		if(validadorDataInicio(dataInicio) == false) {
			throw new BusinessException("Insira uma data inicial existente");
		}
		
		if(validadorDataFim(dataFim) == false) {
			throw new BusinessException("Insira uma data final existente");
		}
		
		if(nomeVotacao.isBlank()) {
			throw new BusinessException("Preencha o nome da votação");
		}
		
		
		if(nomeVotacao.length() > 30) {
			throw new BusinessException("Nome da votação não pode exceder 30 caracteres");
		}
 		
		if(dataInicio.before(now)) {
			
			throw new BusinessException("A votação deve iniciar em uma data atual!");
		}
		
		if(dataFim.before(now)) {
			
			throw new BusinessException("A votação deve ser finalizada em uma data atual ou futura!");
		}
		
		if(!dataFim.after(dataInicio)) {
			
			throw new BusinessException("A data de termino deve ser após a data de inicio!");
			
		}
		
	}
		
		
		public Boolean validadorDataInicio(Date dataInicio) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			
			String dataParaString = dateFormat.format(dataInicio);
			return dataIsValid(dataParaString);
		}
		
		public Boolean validadorDataFim(Date dataFim) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			
			String dataParaString = dateFormat.format(dataFim);
			return dataIsValid(dataParaString);
		}
		
//	    public static boolean dataIsValid(String dataHora) {
//	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//
//	        try {
//	            LocalDateTime parsedDateTime = LocalDateTime.parse(dataHora, formatter);
//	            
//	            if (!dataHora.equals(parsedDateTime.format(formatter))) {
//	                return false;
//	            }
//
//	            return true;
//	        } catch (Exception e) {
//	            return false;
//	        }
//	    }
		
//		public static boolean dataIsValid(String dataHora) {
//	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//	        sdf.setLenient(false);
//
//	        try {
//	            Date parsedDate = sdf.parse(dataHora);
//
//	            if (!dataHora.equals(sdf.format(parsedDate))) {
//	                return false;
//	            }
//
//	            Calendar calendar = Calendar.getInstance();
//	            calendar.setTime(parsedDate);
//
//	            int ano = calendar.get(Calendar.YEAR);
//	            int mes = calendar.get(Calendar.MONTH) + 1;
//	            int dia = calendar.get(Calendar.DAY_OF_MONTH);
//	            int horas = calendar.get(Calendar.HOUR_OF_DAY);
//	            int minutos = calendar.get(Calendar.MINUTE);
//	            int segundos = calendar.get(Calendar.SECOND);
//
//	            if (ano < 1000 || mes < 1 || mes > 12 || dia < 1 || dia > 31 ||
//	                horas < 0 || horas > 23 || minutos < 0 || minutos > 59 || segundos < 0 || segundos > 59) {
//	                return false;
//	            }
//
//	            return true;
//	        } catch (ParseException e) {
//	            return false;
//	        }
//	    }
		
		public Boolean dataIsValid(String data) {
		
		DateTimeFormatter dateFormatter = DateTimeFormatter
				.ofPattern("dd/MM/yyyy HH:mm:ss")
				.withResolverStyle(ResolverStyle.STRICT);
		
		try {
			LocalDate d = LocalDate.parse(data, dateFormatter);
			return true;
		} catch(DateTimeParseException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public void atualizarVotacao(int idVotacao, String nome_votacao, Date dataInicio, Date dataFim, String tipoVotacao) throws BusinessException {
		
	//	validarAtualizacao(idVotacao, nome_votacao, dataInicio, dataFim, tipoVotacao);
		
		votacaoRepository.updateVotacao(idVotacao, nome_votacao, dataInicio, dataFim, tipoVotacao);
	}
	
	public void validarAtualizacao(int idVotacao, String nome_votacao, Date dataInicio, Date dataFim, String tipoVotacao) throws BusinessException {
		
	//	alterar levando em considerações as condições do metodo a ser validado
		if(votacaoRepository.searchVotacaoById(idVotacao) == null) {
			
			throw new BusinessException("Votação informada não encontrada!");
		}
		
		if(!validadorDataInicio(dataInicio)) {
			
			throw new BusinessException("Informe uma data inicial válida!");
		}
		
		if(!validadorDataFim(dataFim)) {
			
			throw new BusinessException("Informe uma data final válida!");
		}
		
		if(dataInicio.before(now)) {
			
			throw new BusinessException("A nova data inicial deve ser atual ou futura!");
		}
		
		if(dataFim.before(now)) {
			
			throw new BusinessException("A nova data de termino deve ser atual ou futura!");
		}
		
		if(!dataFim.after(dataInicio)) {
			
			throw new BusinessException("A nova data de termino deve ser após a data de inicio!");
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
	
	public void checarInicio(String numero) throws BusinessException {
		
		Candidato cdt = candidatoRepository.searchCandidatoById(numero);
		
		int idVotacao = cdt.getId_votacao();
		
		Votacao vtc = votacaoRepository.searchVotacaoById(idVotacao);
		
		if(now.before(vtc.getData_inicio())) {
			
			throw new BusinessException("Aguarde o inicio da votação!");
			
		}
	}
	
	//criar excessões para metodos de checagem

	public void checarTermino(String numero) throws BusinessException {
	
	Candidato cdt = candidatoRepository.searchCandidatoById(numero);
	
	int idVotacao = cdt.getId_votacao();
	
	Votacao vtc = votacaoRepository.searchVotacaoById(idVotacao);
	
	if(now.after(vtc.getData_fim())) {
		
		throw new BusinessException("Votação não recebe mais votos pois está finalizada!");
		
	}
	
	//criar excessões para metodos de checagem
	
}
	
	public Votacao buscarVotacaoNome(String nome) {
		
		return votacaoRepository.searchVotacaoByNome(nome);
		
	}
	
	public void checarTipoVotacao(String tipoVotacao) throws BusinessException {
		if (tipoVotacao.isEmpty()) {
			throw new BusinessException("Selecione um tipo de votação!");
		}
	}
	
	public ResultSet exibirIdVotacaoCandidatos() {

		return votacaoRepository.addIdVotacaoCandidatosCombobox();

	}
	
	public ResultSet exibirIdVotacaoProposta() {

		return votacaoRepository.addIdVotacaoPropostasCombobox();

	}
	
	public Votacao buscarVotacaoId(int idVotacao) {
		
		return votacaoRepository.searchVotacaoById(idVotacao);
	}
	
	public List<Votacao> ExibirVotacoes() {
		return votacaoRepository.obterTodasVotacoes();
		
	}

}
 			
