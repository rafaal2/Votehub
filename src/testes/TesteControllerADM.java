package testes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.votehub.controller.ControllerAdm;
import br.com.votehub.controller.BusinessException;
import br.com.votehub.model.DAOs.AdmDAO;

public class TesteControllerADM {

    private ControllerAdm controllerAdm;
    private AdmDAO admRepositoryMock;

    @BeforeEach
    void setUp() {
        admRepositoryMock = mock(AdmDAO.class);
        controllerAdm = new ControllerAdm();
    }
    
    @Test
    void testeRegistrarAdmCasoPadrao() {
        assertDoesNotThrow(() -> controllerAdm.registrarAdm(1, "admin", "senha123"));
    }
    
    @Test
    void testeRegistrarAdmCamposEmBranco() {	
    	BusinessException e = assertThrows(BusinessException.class,
                () -> controllerAdm.registrarAdm(2, "", ""));
        assertEquals("Todos os campos devem estar preenchidos.", e.getMessage());
    }
    
    @Test
    void testeRegistrarAdmSenhaLonga() {    
    	BusinessException e = assertThrows(BusinessException.class,
                () -> controllerAdm.registrarAdm(3, "login", "111111111111111111111"));
        assertEquals("A senha informada deve possuir limite de 20 caracteres!", e.getMessage());
    }
    
    @Test
    void testeRegistrarAdmLoginLongo() {
    	BusinessException e = assertThrows(BusinessException.class,
                () -> controllerAdm.registrarAdm(4, "1111111111111111111111111111111", "senha123"));
        assertEquals("O login deve possuir limite de 30 caracteres!", e.getMessage());
    }
    
    @Test
    void testeDeletarAdmCasoPadrao() {
        assertDoesNotThrow(() -> controllerAdm.deletarAdm(1));
    }
    
    @Test
    void testeAtualizarAdmCasoPadrao() {
    	assertDoesNotThrow(() -> controllerAdm.atualizarAdm(1, "novoLogin", "novaSenha"));
    }
    
    @Test
    void testeLoginIncorreto() {
        BusinessException e = assertThrows(BusinessException.class,
                () -> controllerAdm.verificarloginadm("usuarioNaoExistente"));
        assertEquals("login incorreta", e.getMessage());
    }
    
}