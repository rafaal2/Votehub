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
		Adm adm = new Adm("Bruno", "Cartaxo123");

		assertEquals("Bruno", adm.getLogin());
		assertEquals("Cartaxo123", adm.getSenha());
	}

	@Test
	void modificarObjetoAdm() {
		Adm adm = new Adm("Bruno", "Cartaxo123");

		adm.setId_adm(1);
		adm.setLogin("Cartaxo");
		adm.setSenha("Bruno123");

		assertEquals(1, adm.getId_adm());
		assertEquals("Cartaxo", adm.getLogin());
		assertEquals("Bruno123", adm.getSenha());
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
	void modificarObjetoCandidato() {
		Candidato candidato = new Candidato("1", "Bruno", "Diretor", 1, "imgcandidato");

		candidato.setNome("Kaio");
		candidato.setNumero_candidato("2");
		candidato.setCargo("Reitor");
		candidato.setId_votacao(2);
		candidato.setImg_candidato("imgcandidato2");

		assertEquals("2", candidato.getNumero_candidato());
		assertEquals("Kaio", candidato.getNome());
		assertEquals("Reitor", candidato.getCargo());
		assertEquals(2, candidato.getId_votacao());
		assertEquals("imgcandidato2", candidato.getImg_candidato());

	}

	@Test
	void criarObjetoVotante() {
		Votante votante = new Votante("ADS2023PL0100", "Bruno", "12345678");
		Votante votanteDois = new Votante("ADS2023PL0200", "Kaio");

		assertEquals("ADS2023PL0100", votante.getMatricula());
		assertEquals("Bruno", votante.getNome());
		assertEquals("12345678", votante.getSenha());

		assertEquals("ADS2023PL0200", votanteDois.getMatricula());
		assertEquals("Kaio", votanteDois.getNome());
	}

	@Test
	void modificarObjetoVotante() {
		Votante votante = new Votante("ADS2023PL0100", "Bruno", "12345678");

		votante.setId_votante(2);
		votante.setMatricula("12345");
		votante.setNome("Kaio");
		votante.setSenha("87654321");

		assertEquals(2, votante.getId_votante());
		assertEquals("12345", votante.getMatricula());
		assertEquals("Kaio", votante.getNome());
		assertEquals("87654321", votante.getSenha());
	}

	@Test
	void criarObjetoProposta() {
		Proposta proposta = new Proposta("Teste", "Descrição 123", 1);

		assertEquals("Teste", proposta.getTitulo());
		assertEquals("Descrição 123", proposta.getDescricao());
		assertEquals(1, proposta.getId_votacao());
	}

	@Test
	void modificarObjetoProposta() {
		Proposta proposta = new Proposta("Teste", "Descrição 123", 1);

		proposta.setTitulo("Teste2");
		proposta.setDescricao("Descrição 321");
		proposta.setId_votacao(2);
		proposta.setId_Proposta(2);

		assertEquals("Teste2", proposta.getTitulo());
		assertEquals("Descrição 321", proposta.getDescricao());
		assertEquals(2, proposta.getId_votacao());
		assertEquals(2, proposta.getId_Proposta());

	}

	@Test
	void criarObjetoPropostaVotante() {
		PropostaVotante propostaVotante = new PropostaVotante(1, 1);

		assertEquals(1, propostaVotante.getIdVotante());
		assertEquals(1, propostaVotante.getIdProposta());
	}

	@Test
	void modificarObjetoPropostaVotante() {
		PropostaVotante propostaVotante = new PropostaVotante(1, 1);

		propostaVotante.setIdProposta(2);
		propostaVotante.setIdPropostaVotante(2);
		propostaVotante.setIdVotante(2);

		assertEquals(2, propostaVotante.getIdVotante());
		assertEquals(2, propostaVotante.getIdProposta());
		assertEquals(2, propostaVotante.getIdPropostaVotante());
	}

	@Test
	void criarObjetoRespostaProposta() {
		RespostaProposta respostaProposta = new RespostaProposta("Resposta", 1);

		assertEquals("Resposta", respostaProposta.getResposta());
		assertEquals(1, respostaProposta.getId_Proposta());
	}

	@Test
	void modificarObjetoRespostaProposta() {
		RespostaProposta respostaProposta = new RespostaProposta("Resposta", 1);

		respostaProposta.setId_RespostaProposta(2);
		respostaProposta.setId_Proposta(2);
		respostaProposta.setResposta("Não");

		assertEquals(2, respostaProposta.getId_RespostaProposta());
		assertEquals("Não", respostaProposta.getResposta());
		assertEquals(2, respostaProposta.getId_Proposta());
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
	void modificarObjetoVotacao() throws ParseException {

		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Date inicio = formatoData.parse("10/12/2023");
		Date fim = formatoData.parse("11/12/2023");

		Votacao votacao = new Votacao(1, "Votação Diretor", inicio, fim, "Diretor");

		inicio = formatoData.parse("12/12/2023");
		fim = formatoData.parse("13/12/2023");

		votacao.setNome_votacao("Votação Reitor");
		votacao.setData_inicio(inicio);
		votacao.setData_fim(fim);
		votacao.setTipoVotacao("Reitor");
		votacao.setId_votacao(2);

		assertEquals("Votação Reitor", votacao.getNome_votacao(), "Nome da votação não foi modificado corretamente");
		assertEquals(inicio, votacao.getData_inicio(), "Data de início não foi modificada corretamente");
		assertEquals(fim, votacao.getData_fim(), "Data de fim não foi modificada corretamente");
		assertEquals("Reitor", votacao.getTipo_Votacao(), "Tipo de votação não foi modificado corretamente");
		assertEquals(2, votacao.getId_votacao(), "ID de votação não foi modificado corretamente");
		
	}

	@Test
	void criarObjetoVotacaoVotante() {
		VotacaoVotante votacaoVotante = new VotacaoVotante(1, 1);

		assertEquals(1, votacaoVotante.getId_votacao());
		assertEquals(1, votacaoVotante.getId_votante());
	}

	@Test
	void modificarObjetoVotacaoVotante() {
		VotacaoVotante votacaoVotante = new VotacaoVotante(1, 1);

		votacaoVotante.setId_votacao(2);
		votacaoVotante.setId_votante(2);
		votacaoVotante.setId_votacaoVotante(2);

		assertEquals(2, votacaoVotante.getId_votacao());
		assertEquals(2, votacaoVotante.getId_votante());
		assertEquals(2, votacaoVotante.getId_votacaoVotante());
	}

	@Test
	void criarObjetoVoto() {
		Voto voto = new Voto("1");

		assertEquals("1", voto.getNumero_candidato());
	}

	@Test
	void modificarObjetoVoto() {
		Voto voto = new Voto("1");

		voto.setId_voto(2);
		voto.setNumero_candidato("2");

		assertEquals("2", voto.getNumero_candidato());
		assertEquals(2, voto.getId_voto());
	}

}
