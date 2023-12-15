package testes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.votehub.controller.ControllerPropostaVotante;
import br.com.votehub.model.DAOs.PropostaVotanteDAO;
import br.com.votehub.model.vo.PropostaVotante;
import br.com.votehub.controller.BusinessException;

class TesteControllerPropostaVotante {

    private ControllerPropostaVotante controllerPropostaVotante;
    private PropostaVotanteDAO propostaVotanteRepository;

    @BeforeEach
    void setUp() {
        propostaVotanteRepository = mock(PropostaVotanteDAO.class);
        controllerPropostaVotante = new ControllerPropostaVotante();
        controllerPropostaVotante.setPropostaVotanteRepository(propostaVotanteRepository);
    }

    @Test
    void testeAdicionarPropostaVotante() {
        int idVotante = 1;
        int idProposta = 2;

        doNothing().when(propostaVotanteRepository).addpropostaVotante(any(PropostaVotante.class));

        controllerPropostaVotante.adicionarPropostaVotante(idVotante, idProposta);

        verify(propostaVotanteRepository).addpropostaVotante(any(PropostaVotante.class));
    }

    @Test
    void testeChecarRespostaUnicaVotanteNaoRegistrado() throws Exception {
        int idVotante = 1;
        int idProposta = 2;

        when(propostaVotanteRepository.verificarRespostaUnica(idVotante, idProposta)).thenReturn(false);

        assertDoesNotThrow(() -> controllerPropostaVotante.checarRespostaUnica(idVotante, idProposta));
    }

    @Test
    void testeChecarRespostaUnicaVotanteRegistrado() throws Exception {
        int idVotante = 1;
        int idProposta = 2;

        when(propostaVotanteRepository.verificarRespostaUnica(idVotante, idProposta)).thenReturn(true);

        BusinessException exception = assertThrows(BusinessException.class, () -> controllerPropostaVotante.checarRespostaUnica(idVotante, idProposta));

        assertEquals("Votante já possui resposta registrada nessa proposta.", exception.getMessage());
    }
}
