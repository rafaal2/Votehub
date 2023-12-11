package testes;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerProposta;

class TesteControllerProposta {

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	ControllerProposta props = new ControllerProposta();
	
	@Test
	public void TesteregistrarPropostaSucesso() throws SQLException {
		 try {
	            props.registrarProposta("porposta 1", "testeteste", 1);
	        } catch (BusinessException e) {
	            fail("Não é esperada exceção se o registro for válido");
	        }
		}
	}

