package testes;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerProposta;
import br.com.votehub.model.vo.Voto;

class TesteControllerProposta {
	@Mock
	private ControllerProposta daoMock;

	@BeforeEach
	void setUp() throws BusinessException, SQLException {
		MockitoAnnotations.initMocks(this);
		 doThrow(new BusinessException("o titulo deve ser preenchido."))
         .when(daoMock).validarRegistro(eq(""), any(), eq(1));

		 doThrow(new BusinessException("o titulo não pode exceder 150 caracteres."))
         .when(daoMock).validarRegistro(eq("proposta 1".repeat(151)), eq("testeteste"), eq(1));
		 
		 doThrow(new BusinessException("a descrição deve ser preenchida."))
         .when(daoMock).validarRegistro(anyString(), eq(""), eq(1));

		 doThrow(new BusinessException("a descrição não pode exceder 2000 caracteres."))
         .when(daoMock).validarRegistro(anyString(), eq("testeteste".repeat(100)), eq(1));
	}

	@Test
	public void testRegistrarPropostaSucesso() throws SQLException, BusinessException {
		doNothing().when(daoMock).registrarProposta(anyString(), anyString(), anyInt());
		daoMock.registrarProposta("proposta 1", "testeteste", 1);
		verify(daoMock).registrarProposta("proposta 1", "testeteste", 1);
	}

	@Test
	void testeValidarRegistro() throws BusinessException, SQLException {
		doNothing().when(daoMock).validarRegistro("proposta 1", "testeteste", 1);

		assertDoesNotThrow(() -> daoMock.validarRegistro("proposta 1", "testeteste", 1));
	}


	@Test
	void testeValidarRegistroCampoVazio() throws BusinessException, SQLException {
	    BusinessException exception = Assertions.assertThrows(BusinessException.class,
	            () -> daoMock.validarRegistro("", null, 1)); 

	    assertEquals("o titulo deve ser preenchido.", exception.getMessage());
	}

	@Test
	void testeValidarRegistroCampoLongo() throws BusinessException, SQLException {
	    BusinessException exception = Assertions.assertThrows(BusinessException.class,
	            () -> daoMock.validarRegistro("proposta 1".repeat(151), "testeteste", 1));

	    assertEquals("o titulo não pode exceder 150 caracteres.", exception.getMessage());
	}
	
	@Test
	void testeValidarRegistroCampoVazio1() throws BusinessException, SQLException {
	    BusinessException exception = Assertions.assertThrows(BusinessException.class,
	            () -> daoMock.validarRegistro("", "", 1)); 

	    assertEquals("a descrição deve ser preenchida.", exception.getMessage());
	}

	@Test
	void testeValidarRegistroCampoLongo1() throws BusinessException, SQLException {
	    BusinessException exception = Assertions.assertThrows(BusinessException.class,
	            () -> daoMock.validarRegistro("proposta 1", "testeteste".repeat(100), 1));
	    assertEquals("a descrição não pode exceder 2000 caracteres.", exception.getMessage());
	}
	
//	@Test
//    void testeValidarExclusaoPropostaExistente() {
//        when(votoRepository.searchVotoById(1)).thenReturn(new Voto("123"));
//
//        assertDoesNotThrow(() -> controllerVoto.validarExclusao(1));
//    }
//
//	@Test
//    void testeValidarExclusaoPropostaInexistente() {
//        when(votoRepository.searchVotoById(2)).thenReturn(null);
//
//        BusinessException exception = assertThrows(BusinessException.class, () -> controllerVoto.validarExclusao(2));
//        assertEquals("Voto não encontrado!", exception.getMessage());
//    }
//	
//	@Test
//	void testeDeletarProposta() throws BusinessException {
//
//		int idVoto = 1;
//		when(votoRepository.searchVotoById(idVoto)).thenReturn(new Voto("123"));
//
//		assertDoesNotThrow(() -> controllerVoto.deletarVoto(idVoto));
//
//		verify(votoRepository).deleteVoto(idVoto);
//	}
//
//

}




