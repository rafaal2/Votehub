package testes;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerCandidato;
import br.com.votehub.model.DAOs.CandidatoDAO;
import br.com.votehub.model.vo.Candidato;

class TesteControllerCandidato {

	private ControllerCandidato controllerCandidato;
	private CandidatoDAO candidatoRepository;

	@BeforeEach
	void setUp() {
		candidatoRepository = mock(CandidatoDAO.class);
		controllerCandidato = new ControllerCandidato();
		controllerCandidato.setCandidatoRepository(candidatoRepository);
	}

	@Test
	void testeRegistrarCandidato() throws BusinessException, SQLException {

		String numeroCandidato = "113";
		String nome = "George";
		String cargo = "Diretor";
		int id_votacao = 1;
		String img_candidato = "image.jpg";

		when(candidatoRepository.verificarSeNumeroExiste(any())).thenReturn(false);
		doNothing().when(candidatoRepository).addCandidato(any());

		assertDoesNotThrow(
				() -> controllerCandidato.registrarCandidato(numeroCandidato, nome, cargo, id_votacao, img_candidato));

		verify(candidatoRepository).addCandidato(any());
	}

	@Test
	void testeValidarRegistro() throws BusinessException, SQLException {

		String numeroCandidato = "113";
		String nome = "George";
		String cargo = "Diretor";
		int id_votacao = 1;
		String img_candidato = "image.jpg";

		when(candidatoRepository.verificarSeNumeroExiste(any())).thenReturn(false);

		assertDoesNotThrow(
				() -> controllerCandidato.validarRegistro(numeroCandidato, nome, cargo, id_votacao, img_candidato));
	}

	@Test
	void testeValidarRegistroNumeroCandidatoJaExistente() throws SQLException {
		String numeroCandidato = "113";
		String nome = "George";
		String cargo = "Diretor";
		int id_votacao = 1;
		String img_candidato = "image.jpg";

		when(candidatoRepository.verificarSeNumeroExiste(any())).thenReturn(true);

		BusinessException exception = assertThrows(BusinessException.class,
				() -> controllerCandidato.validarRegistro(numeroCandidato, nome, cargo, id_votacao, img_candidato));

		assertEquals("Número do candidato já está em uso!", exception.getMessage());
	}

	@Test
	void testeValidarRegistroCampoVazio() {
		String numeroCandidato = "";
		String nome = "George";
		String cargo = "Diretor";
		int id_votacao = 1;
		String img_candidato = "image.jpg";

		BusinessException exception = assertThrows(BusinessException.class,
				() -> controllerCandidato.validarRegistro(numeroCandidato, nome, cargo, id_votacao, img_candidato));

		assertEquals("Todos os campos devem estar preenchindos!", exception.getMessage());
	}

	@Test
	void testeValidarRegistroNumeroCandidatoExistente() throws SQLException {
		String numeroCandidato = "113";
		String nome = "George";
		String cargo = "Diretor";
		int id_votacao = 1;
		String img_candidato = "image.jpg";

		when(candidatoRepository.verificarSeNumeroExiste(any())).thenReturn(false);
		when(candidatoRepository.verificarSeNumeroExiste(numeroCandidato)).thenReturn(true);

		BusinessException exception = assertThrows(BusinessException.class,
				() -> controllerCandidato.validarRegistro(numeroCandidato, nome, cargo, id_votacao, img_candidato));

		assertEquals("Número do candidato já está em uso!", exception.getMessage());
	}

	@Test
	void testeValidarAtualizacaoCandidatoNaoEncontrado() {
		String numeroCandidato = "123";
		String nome = "George";
		String cargo = "Diretor";
		int id_votacao = 1;
		String img_candidato = "image.jpg";

		when(candidatoRepository.searchCandidatoById(any())).thenReturn(null);

		BusinessException exception = assertThrows(BusinessException.class,
				() -> controllerCandidato.validarAtualizacao(numeroCandidato, nome, cargo, id_votacao, img_candidato));

		assertEquals("Candidato referido não encontrado", exception.getMessage());
	}

	@Test
	void testeValidarAtualizacaoNumeroCargoLimiteExcedido() {
		String numeroCandidato = "113";
		String nome = "George";
		String cargo = "ABCDEFGHIJKLN".repeat(11);
		int id_votacao = 1;
		String img_candidato = "image.jpg";

		when(candidatoRepository.searchCandidatoById(any()))
				.thenReturn(new Candidato("113", "GG", "ABCDEFGHIJKLN".repeat(11), 1, "image.jpg"));

		BusinessException exception = assertThrows(BusinessException.class,
				() -> controllerCandidato.validarAtualizacao(numeroCandidato, nome, cargo, id_votacao, img_candidato));

		assertEquals("Os valores numero e cargo informados devem possuir limite de 100 caracteres",
				exception.getMessage());
	}

	@Test
	void testeValidarAtualizacaoNomeLimiteExcedido() {
		String numeroCandidato = "113";
		String nome = "ABCDEFGHIJK".repeat(300);
		String cargo = "Diretor";
		int id_votacao = 1;
		String img_candidato = "image.jpg";

		when(candidatoRepository.searchCandidatoById(any())).thenReturn(new Candidato("113", "GG", "ABCDEFGHIJK".repeat(300), 1, "image.jpg"));

		BusinessException exception = assertThrows(BusinessException.class,
				() -> controllerCandidato.validarAtualizacao(numeroCandidato, nome, cargo, id_votacao, img_candidato));

		assertEquals("O nome inserido deve possuir limite de 200 caracteres!", exception.getMessage());
	}

	@Test
	void testeValidarExclusao() throws BusinessException {

		String numeroCandidato = "123";

		when(candidatoRepository.searchCandidatoById(any()))
				.thenReturn(new Candidato("113", "GG", "Diretor", 1, "image.jpg"));

		assertDoesNotThrow(() -> controllerCandidato.validarExclusao(numeroCandidato));
	}

	@Test
	void testeDeletarCandidato() throws BusinessException {

		String numeroCandidato = "113";

		when(candidatoRepository.searchCandidatoById(any()))
				.thenReturn(new Candidato("113", "GG", "Diretor", 1, "image.jpg"));
		doNothing().when(candidatoRepository).deleteCandidato(any());

		assertDoesNotThrow(() -> controllerCandidato.deletarCandidato(numeroCandidato));

		verify(candidatoRepository).deleteCandidato(any());
	}

	@Test
	void testeAtualizarCandidato() throws BusinessException {

		String numeroCandidato = "113";
		String nome = "George";
		String cargo = "Diretor";
		int id_votacao = 1;
		String img_candidato = "image.jpg";

		Candidato candidato = new Candidato("113", "GG", "Diretor", 1, "image.jpg");
		candidatoRepository.addCandidato(candidato);

		when(candidatoRepository.searchCandidatoById("113")).thenReturn(candidato);

		doNothing().when(candidatoRepository).updateCandidato(anyString(), anyString(), anyString(), anyInt(),
				anyString());

		controllerCandidato.atualizarCandidato(numeroCandidato, nome, cargo, id_votacao, img_candidato);

		verify(candidatoRepository).updateCandidato(eq(numeroCandidato), eq(nome), eq(cargo), eq(id_votacao),
				eq(img_candidato));
	}

	@Test
    void testeRecuperarImg() {

        when(candidatoRepository.searchCandidatoImg(any())).thenReturn("image.jpg");

        assertEquals("image.jpg", controllerCandidato.recuperarImg());
    }

	@Test
	void testeBuscarCandidato() {

		String numeroCandidato = "113";

		when(candidatoRepository.searchCandidatoById(any()))
				.thenReturn(new Candidato("113", "GG", "Diretor", 1, "image.jpg"));

		assertNotNull(controllerCandidato.buscarCandidato(numeroCandidato));
	}

}
