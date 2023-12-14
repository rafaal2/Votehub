package testes;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerProposta;

class TesteControllerProposta {
    @Mock
    private ControllerProposta daoMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegistrarPropostaSucesso() throws SQLException, BusinessException {
        doNothing().when(daoMock).registrarProposta(anyString(), anyString(), anyInt());

        daoMock.registrarProposta("proposta 1", "testeteste", 1);
        verify(daoMock).registrarProposta("proposta 1", "testeteste", 1);

    }
}
