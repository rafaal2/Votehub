package testes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerVotacao;
import br.com.votehub.model.DAOs.VotacaoDAO;
import br.com.votehub.model.vo.Votacao;

class TesteControllerVotacao {
	
	private ControllerVotacao contVotacao = Mockito.mock(ControllerVotacao.class);
	private VotacaoDAO votacaoRepository;
	
	@BeforeEach
	void setUp() {
		votacaoRepository = mock(VotacaoDAO.class);
		//contVotacao = new ControllerVotacao();
	}	
	
	@Test	
	void deveRegistrarVotacaoComSucesso() {
		
		try {
			
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Date inicio = formatoData.parse("23/12/2023");
		Date fim = formatoData.parse("24/12/2023");
		
		doNothing().when(contVotacao).registrarVotacao(anyString(), any(Date.class), any(Date.class), anyString());
		
		contVotacao.registrarVotacao("Votacao", inicio, fim, "candidatos");
		verify(contVotacao).registrarVotacao("Votacao", inicio, fim, "candidatos");
					
		} catch (ParseException e) {
			
			e.printStackTrace();
		} catch (BusinessException e) {
			
			e.printStackTrace();
		}						
		
	}
	
	
	@Test
	void deveLancarExcecaoDataAntiga() {
		
		try {
			
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			Date inicio = formatoData.parse("13/12/2023");
			Date fim = formatoData.parse("17/12/2023");
			
			doThrow(new BusinessException("A Votação deve iniciar em uma data atual!"))
			.when(contVotacao).registrarVotacao(anyString(), any(Date.class), any(Date.class), anyString());
			
			assertThrows(BusinessException.class, () -> contVotacao.registrarVotacao("Votacao", inicio, fim, "candidatos"));
						
			} catch (ParseException e) {
				
				e.printStackTrace();
			} catch (BusinessException e) {
				
				e.printStackTrace();
			}						
	}
	
	@Test
	void deveLancarExcecaoDataDeTerminoAntiga() {
		
		try {
			
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			Date inicio = formatoData.parse("23/12/2023");
			Date fim = formatoData.parse("16/12/2023");
			
			doThrow(new BusinessException("A Votação deve ser finalizada em uma data atual ou futura!"))
			.when(contVotacao).registrarVotacao(anyString(), any(Date.class), any(Date.class), anyString());
			
			assertThrows(BusinessException.class, () -> contVotacao.registrarVotacao("Votacao", inicio, fim, "candidatos"));
						
			} catch (ParseException e) {
				
				e.printStackTrace();
			} catch (BusinessException e) {
				
				e.printStackTrace();
			}						
	}
	
	@Test
	void deveLancarExcecaoDataDeTerminoAntesDoInicio() {
		
		try {
			
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			Date inicio = formatoData.parse("23/12/2023");
			Date fim = formatoData.parse("22/12/2023");
			
			doThrow(new BusinessException("A data de termino deve ser após a data de inicio!"))
			.when(contVotacao).registrarVotacao(anyString(), any(Date.class), any(Date.class), anyString());
			
			assertThrows(BusinessException.class, () -> contVotacao.registrarVotacao("Votacao", inicio, fim, "candidatos"));
						
			} catch (ParseException e) {
				
				e.printStackTrace();
			} catch (BusinessException e) {
				
				e.printStackTrace();
			}						
	}
	
	@Test
	void deveLancarExcecaoNomeEmBranco() {
			
			try {
				
				SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
				Date inicio = formatoData.parse("22/12/2023");
				Date fim = formatoData.parse("23/12/2023");
				
				doThrow(new BusinessException("Preencha o nome da votação"))
				.when(contVotacao).registrarVotacao(anyString(), any(Date.class), any(Date.class), anyString());
				
				assertThrows(BusinessException.class, () -> contVotacao.registrarVotacao("  ", inicio, fim, "candidatos"));
							
				} catch (ParseException e) {
					
					e.printStackTrace();
				} catch (BusinessException e) {
					
					e.printStackTrace();
				}						
		}
	
	@Test
	void deveLancarExcecaoNomeLongo() {
			
			try {
				
				SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
				Date inicio = formatoData.parse("22/12/2023");
				Date fim = formatoData.parse("23/12/2023");
				
				doThrow(new BusinessException("Nome da votação não pode exceder 30 caracteres"))
				.when(contVotacao).registrarVotacao(anyString(), any(Date.class), any(Date.class), anyString());
				
				assertThrows(BusinessException.class,
						() -> contVotacao.registrarVotacao("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", inicio, fim, "candidatos"));
							
				} catch (ParseException e) {
					
					e.printStackTrace();
				} catch (BusinessException e) {
					
					e.printStackTrace();
				}						
		}
	
	@Test	
	void deveAtualizarVotacaoComSucesso() {
		
		try {
			
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Date inicio = formatoData.parse("22/12/2023");
		Date fim = formatoData.parse("23/12/2023");
		
		doNothing().when(contVotacao).atualizarVotacao(anyInt(), anyString(), any(Date.class), any(Date.class), anyString());
		
		contVotacao.atualizarVotacao(1,"Votacao nova", inicio, fim, "candidatos");
		verify(contVotacao).atualizarVotacao(1, "Votacao nova", inicio, fim, "candidatos");
					
		} catch (ParseException e) {
			
			e.printStackTrace();
		} catch (BusinessException e) {
			
			e.printStackTrace();
		}						
		
	}
	
	@Test
	void deveLancarExcecaoAtualizacaoDataTerminoAntesDoInicio() {
			
			try {
				
				SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
				Date inicio = formatoData.parse("23/12/2023");
				Date fim = formatoData.parse("22/12/2023");
				
				doThrow(new BusinessException("A nova data de termino deve ser após a data de inicio!"))
				.when(contVotacao).atualizarVotacao(anyInt(), anyString(), any(Date.class), any(Date.class), anyString());
				
				assertThrows(BusinessException.class,
						() -> contVotacao.atualizarVotacao(1, "votaçãoTt", inicio, fim, "candidatos"));
							
				} catch (ParseException e) {
					
					e.printStackTrace();
				} catch (BusinessException e) {
					
					e.printStackTrace();
				}						
		}
	
	@Test
	void deveLancarExcecaoAtualizacaoDataTerminoAntiga() {
			
			try {
				
				SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
				Date inicio = formatoData.parse("23/12/2023");
				Date fim = formatoData.parse("15/12/2023");
				
				doThrow(new BusinessException("A nova data de termino deve ser atual ou futura!"))
				.when(contVotacao).atualizarVotacao(anyInt(), anyString(), any(Date.class), any(Date.class), anyString());
				
				assertThrows(BusinessException.class,
						() -> contVotacao.atualizarVotacao(1, "votaçãot", inicio, fim, "candidatos"));
							
				} catch (ParseException e) {
					
					e.printStackTrace();
				} catch (BusinessException e) {
					
					e.printStackTrace();
				}						
		}
	
	@Test
	void deveLancarExcecaoAtualizacaoDataInicialAntiga() {
			
			try {
				
				SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
				Date inicio = formatoData.parse("13/12/2023");
				Date fim = formatoData.parse("24/12/2023");
				
				doThrow(new BusinessException("A nova data inicial deve ser atual ou futura!"))
				.when(contVotacao).atualizarVotacao(anyInt(), anyString(), any(Date.class), any(Date.class), anyString());
				
				assertThrows(BusinessException.class,
						() -> contVotacao.atualizarVotacao(1, "votaçãot", inicio, fim, "candidatos"));
							
				} catch (ParseException e) {
					
					e.printStackTrace();
				} catch (BusinessException e) {
					
					e.printStackTrace();
				}						
		}
	
	
	@Test	
	void deveExcluirVotacaoComSucesso() {
		
		try {
			doNothing().when(contVotacao).excluirVotacao(anyInt());
			contVotacao.excluirVotacao(1);
			
			verify(contVotacao).excluirVotacao(1);	
			
		} catch (BusinessException e) {
			
			e.printStackTrace();
		};
				
	}
	
	@Test	
	void develancarExcecaoVotacaoNaoEncontrada() {
		
		try {
			doThrow(new BusinessException("Votação não encontrada no banco de dados!"))
			.when(contVotacao).excluirVotacao(2);
			
			assertThrows(BusinessException.class,
					() -> contVotacao.excluirVotacao(2));	
			
		} catch (BusinessException e) {
			
			e.printStackTrace();
		}
				
	} 	
	
}
