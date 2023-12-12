package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerVotacao;
import br.com.votehub.model.vo.Votacao;

class TesteControllerVotacao {

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	ControllerVotacao contVotacao = new ControllerVotacao();
	
	@Test
	public void testAdicionarVotacao() {
		
		try {
			
			SimpleDateFormat formatacao = new SimpleDateFormat("dd/MM/yyyy");
			Date inicio = formatacao.parse("13/12/2023");
			Date fim = formatacao.parse("14/12/2023");
			
			contVotacao.registrarVotacao("TesteJttt", inicio, fim, "Candidato");
			
		} catch(BusinessException e) {
			
			e.printStackTrace();
			fail("Nesse caso, deve-se registrar a votação sem erros");
			
		} catch (ParseException e) {
			
			fail("Nesse caso, deve-se registrar a votação com data valida");
		}		
		
	}
	
	@Test
	public void testAtualizarVotacao() {
		
		
		try {
			
			SimpleDateFormat formatacao = new SimpleDateFormat("dd/MM/yyyy");
			Date inicio = formatacao.parse("14/12/2023");
			Date fim = formatacao.parse("15/12/2023");
			
			contVotacao.atualizarVotacao(9, "TesteJ", inicio, fim, "Candidato");
			
		} catch (BusinessException e) {
			
			e.printStackTrace();
			fail("Nesse caso, deve-se atualizar a votação sem erros");
		} catch (ParseException e) {
			
			e.printStackTrace();
			fail("Nesse caso, deve-se atualizar a votação com data valida");
		}
	}
	
	@Test
	public void testProcurarVotacaoPorId() {
		
		
		try {
			
			SimpleDateFormat formatacao = new SimpleDateFormat("dd/MM/yyyy");
			Date inicio = formatacao.parse("14/12/2023");
			Date fim = formatacao.parse("15/12/2023");
			
			Votacao votacaoTeste = contVotacao.buscarVotacaoNome("TesteJ");
			
		//	assertEquals(9, votacaoTeste.getId_votacao());
			assertEquals("TesteJ", votacaoTeste.getNome_votacao());
			assertEquals(inicio, votacaoTeste.getData_inicio());
			assertEquals(fim, votacaoTeste.getData_fim());
			assertEquals("Candidato", votacaoTeste.getTipo_Votacao());
			
		} catch (ParseException e) {
			
			e.printStackTrace();
			fail("ParseException indesejada");
			
		}
		
	}
	
	@Test
	public void testarDelecaoDeVotacao() {
		
		contVotacao.excluirVotacao(9);
		
	}

}
