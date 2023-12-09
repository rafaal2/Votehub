package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerAdm;

public class TesteControllerADM {
	
ControllerAdm controller = new ControllerAdm();

	@Test
	public void testeRegistroVálido() throws BusinessException {
        
        try {
            controller.registrarAdm(1, "admin", "senha123");
        } catch (BusinessException e) {
            fail("Não é esperada exceção se o registro for válido");
        }
	}
	
//	@Test
//	public void testeIdExistente() throws BusinessException {
//        
//        try {
//            controller.registrarAdm(1, "admin", "senha123");
//            
//        } catch (BusinessException e) {
//        	fail("Lançar excessão para ID repetido.");
//        	assertEquals("ID informado já existe!", e.getMessage());
//        }
//	}
	
	@Test
	public void testeCamposBranco() throws BusinessException {
		try {
            controller.registrarAdm(2, "", "");
            fail("Lançar uma exceção para campos em branco");
        } catch (BusinessException e) {
            assertEquals("Todos os campos devem estar preenchidos.", e.getMessage());
            
        }
	}
	
	@Test
	public void testeSenhaLonga() throws BusinessException {    try {
            controller.registrarAdm(3, "11111111111", "11111111111");
            fail("Lançar uma exceção para senha muito longa");
        } catch (BusinessException e) {
            assertEquals("A senha informada deve possuir limite de 10 caracteres!", e.getMessage());
        }
	}
	
	@Test
	public void testeLoginLongo() throws BusinessException { 
	try {
            controller.registrarAdm(4, "11111111111", "senha123");
            fail("Lançar uma exceção para login muito longo");
        } catch (BusinessException e) {
            assertEquals("O login deve possuir limite de 10 caracteres!", e.getMessage());
        }
    }
		
}