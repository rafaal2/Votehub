package testes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.votehub.controller.ControllerVoto;
import br.com.votehub.model.DAOs.CandidatoDAO;
import br.com.votehub.model.DAOs.VotoDAO;
import br.com.votehub.model.vo.Candidato;
import br.com.votehub.model.vo.Voto;
import br.com.votehub.controller.BusinessException;

class TesteControllerVoto {

	private ControllerVoto controllerVoto;
	private VotoDAO votoRepository;
	private CandidatoDAO candidatoRepository;

	@BeforeEach
	void setUp() {
		votoRepository = mock(VotoDAO.class);
		candidatoRepository = mock(CandidatoDAO.class);
		controllerVoto = new ControllerVoto();
		controllerVoto.setVotoRepository(votoRepository);
		controllerVoto.setCandidatoRepository(candidatoRepository);  
	}

	@Test
    void testeRegistrarVoto() throws BusinessException {
 
        when(candidatoRepository.searchCandidatoById(anyString())).thenReturn(new Candidato("123", "Nome Candidato", "Presidente", 1, "imagem.jpg"));

        controllerVoto.registrarVoto("123");

        verify(votoRepository).addVoto(any());
    }

	@Test
    void testeValidarRegistro() {
        when(candidatoRepository.searchCandidatoById(anyString())).thenReturn(new Candidato("123", "Nome Candidato", "Presidente", 1, "imagem.jpg"));

        assertDoesNotThrow(() -> controllerVoto.validarRegistro("123"));
        assertThrows(BusinessException.class, () -> controllerVoto.validarRegistro(""));
    }

	@Test
	void testeValidarRegistroCandidatoExistente() {
	    when(candidatoRepository.searchCandidatoById("123")).thenReturn(new Candidato("123", "Nome Candidato", "Presidente", 1, "imagem.jpg"));

	    assertDoesNotThrow(() -> controllerVoto.validarRegistro("123"));
	}

	@Test
    void testeValidarRegistroCandidatoInexistente() {
    	
        when(candidatoRepository.searchCandidatoById("456")).thenReturn(null);

        BusinessException exception = assertThrows(BusinessException.class, () -> controllerVoto.validarRegistro("456"));
        assertEquals("Candidato inexistente, informe um numero valido!", exception.getMessage());
    }

	@Test
	void testeValidarRegistroCampoVazio() {
		
		controllerVoto.setCandidatoRepository(mock(CandidatoDAO.class));

	    BusinessException exception = Assertions.assertThrows(BusinessException.class,
	            () -> controllerVoto.validarRegistro(null));

	    assertEquals("O campo numero de candidato não pode estar vazio!", exception.getMessage());
	}

	@Test
	void testeValidarRegistroCampoLongo() {
		
		BusinessException exception = Assertions.assertThrows(BusinessException.class, 
				() -> {controllerVoto.validarRegistro("1234567890".repeat(11));});
		
		assertEquals("O numero de candidato não pode possuir mais do que 100 caracteres", exception.getMessage());
		
	}

	@Test
    void testeValidarExclusaoVotoExistente() {
        when(votoRepository.searchVotoById(1)).thenReturn(new Voto("123"));

        assertDoesNotThrow(() -> controllerVoto.validarExclusao(1));
    }

	@Test
    void testeValidarExclusaoVotoInexistente() {
        when(votoRepository.searchVotoById(2)).thenReturn(null);

        BusinessException exception = assertThrows(BusinessException.class, () -> controllerVoto.validarExclusao(2));
        assertEquals("Voto não encontrado!", exception.getMessage());
    }

	@Test
	void testeDeletarVoto() throws BusinessException {

		int idVoto = 1;
		when(votoRepository.searchVotoById(idVoto)).thenReturn(new Voto("123"));

		assertDoesNotThrow(() -> controllerVoto.deletarVoto(idVoto));

		verify(votoRepository).deleteVoto(idVoto);
	}

	@Test
	void testeConsultarVoto() {

		int idVoto = 1;
		when(votoRepository.searchVotoById(idVoto)).thenReturn(new Voto("123"));

		controllerVoto.consultarVoto(idVoto);

		verify(votoRepository).searchVotoById(idVoto);
	}

	@Test
	void testeValidarConsulta() {

		int idVotoExistente = 1;
		int idVotoInexistente = 2;
		when(votoRepository.searchVotoById(idVotoExistente)).thenReturn(new Voto("123"));
		when(votoRepository.searchVotoById(idVotoInexistente)).thenReturn(null);

		assertDoesNotThrow(() -> controllerVoto.validarConsulta(idVotoExistente));
		BusinessException exception = assertThrows(BusinessException.class,
				() -> controllerVoto.validarConsulta(idVotoInexistente));
		assertEquals("Voto não encontrado, indorme um identificador valido!", exception.getMessage());
	}

	@Test
	void testeApurarDiretor() throws BusinessException {

		assertDoesNotThrow(() -> controllerVoto.ApurarDiretor(1));

		verify(votoRepository).apurarVotosDiretor();
	}

	@Test
	void testeApurarReitor() throws BusinessException {

		assertDoesNotThrow(() -> controllerVoto.ApurarReitor(1));

		verify(votoRepository).apurarVotosReitor();
	}
}
