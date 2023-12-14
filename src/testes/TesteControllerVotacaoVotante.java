package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import br.com.votehub.controller.ControllerVotacaoVotante;
import br.com.votehub.controller.BusinessException;
import br.com.votehub.model.DAOs.VotacaoVotanteDAO;

class TesteControllerVotacaoVotante {

	private ControllerVotacaoVotante controllerVotacaoVotante;
    private VotacaoVotanteDAO votacaoVotanteRepository;
	
    @BeforeEach
    void setUp() {
    	votacaoVotanteRepository = mock(VotacaoVotanteDAO.class);
        controllerVotacaoVotante = new ControllerVotacaoVotante();
        controllerVotacaoVotante.setVotacaoVotanteRepository(votacaoVotanteRepository);
    }

    @Test
    void testRegistrarVotacaoVotante() {
    	
        int idVotacao = 1;
        int idVotante = 2;

        assertDoesNotThrow(() -> controllerVotacaoVotante.registrarVotacaoVotante(idVotacao, idVotante));

        verify(votacaoVotanteRepository).addVotacaoVotante(any());
    }

    @Test
    void testChecarVotabilidadeVotanteSemVoto() throws SQLException {

        int idVotante = 2;
        when(votacaoVotanteRepository.verificarVotoUnico(idVotante)).thenReturn(false);

        assertDoesNotThrow(() -> controllerVotacaoVotante.checarVotabilidade(idVotante));
    }

    @Test
    void testChecarVotabilidadeVotanteComVoto() throws SQLException {
    	
    	int idVotante = 2;
        when(votacaoVotanteRepository.verificarVotoUnico(idVotante)).thenReturn(true);

        BusinessException exception = assertThrows(BusinessException.class, () -> controllerVotacaoVotante.checarVotabilidade(idVotante));
        assertEquals("Votante já possui voto registrado!", exception.getMessage());
    }
}
