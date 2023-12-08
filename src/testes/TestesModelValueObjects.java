package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.votehub.model.vo.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class TestesModelValueObjects {

	@Test
	void criarObjetoAdm() {
		Adm adm1 = new Adm("Bruno", "Cartaxo123");
		
		assertEquals("Bruno", adm1.getLogin());
	    assertEquals("Cartaxo123", adm1.getSenha());
	}
	
	@Test
	void criarObjetoCandidato() {
		Candidato candidato = new Candidato("1", "Bruno", "Diretor", 1, "imgcandidato");
		
		assertEquals("1", candidato.getNumero_candidato());
        assertEquals("Bruno", candidato.getNome());
        assertEquals("Diretor", candidato.getCargo());
        assertEquals(1, candidato.getId_votacao());
        assertEquals("imgcandidato", candidato.getImg_candidato());
		
	}
	
	@Test
	void criarObjetoVotante() {
		Votante votante = new Votante("ADS2023PL0100", "Bruno", "12345678");
		
		assertEquals("ADS2023PL0100", votante.getMatricula());
		assertEquals("Bruno", votante.getNome());
		assertEquals("12345678", votante.getSenha());
	}
	
	@Test
	void criarObjetoProposta() {
		Proposta proposta = new Proposta("Teste", "Descrição 123", 1);
		
		assertEquals("Teste", proposta.getTitulo());
		assertEquals("Descrição 123", proposta.getDescricao());
		assertEquals(1, proposta.getId_votacao());
	}
	
	@Test
	void criarObjetoPropostaVotante() {
		PropostaVotante propostaVotante = new PropostaVotante(1, 1);
		
		assertEquals(1, propostaVotante.getIdVotante());
		assertEquals(1, propostaVotante.getIdProposta());
	}
	
	@Test
	void criarObjetoRespostaProposta() {
		RespostaProposta respostaProposta = new RespostaProposta("Resposta", 1);
		
		assertEquals("Resposta", respostaProposta.getResposta());
		assertEquals(1, respostaProposta.getId_Proposta());
	}
	
	@Test
	void criarObjetoVotacao() throws ParseException {
		
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Date inicio = formatoData.parse("10/12/2023");
		Date fim = formatoData.parse("11/12/2023");	
		
		Votacao votacao = new Votacao("Votação Diretor", inicio, fim, "Diretor");
		
		assertEquals("Votação Diretor", votacao.getNome_votacao());
		assertEquals(inicio, votacao.getData_inicio());
		assertEquals(fim, votacao.getData_fim());
		assertEquals("Diretor", votacao.getTipo_Votacao());
	}
	
	@Test
	void criarObjetoVotacaoVotante() {
		VotacaoVotante votacaoVotante = new VotacaoVotante(1, 1);
		
		assertEquals(1, votacaoVotante.getId_votacao());
		assertEquals(1, votacaoVotante.getId_votante());
	}
	
	@Test
	void criarObjetoVoto() {
		Voto voto = new Voto("1");
		
		assertEquals("1", voto.getNumero_candidato());
	}

}
