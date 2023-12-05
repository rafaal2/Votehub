package br.com.votehub;

import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.SwingUtilities;
import br.com.votehub.controller.BusinessException;
import br.com.votehub.model.DAOs.AdmDAO;
import br.com.votehub.model.DAOs.CandidatoDAO;
import br.com.votehub.model.DAOs.VotacaoDAO;
import br.com.votehub.model.DAOs.VotanteDAO;
import br.com.votehub.model.DAOs.VotoDAO;
import br.com.votehub.model.vo.Adm;
import br.com.votehub.model.vo.Candidato;
import br.com.votehub.model.vo.Votacao;
import br.com.votehub.model.vo.Votante;
import br.com.votehub.model.vo.Voto;
import br.com.votehub.view.*;
public class Main {
	

	public static void main(String[] args) throws SQLException, BusinessException {
	
	    TelaInicial telaInicial = new TelaInicial();
		telaInicial.setVisible(true);
		//VotoDAO teste = new VotoDAO();
		//teste.apurarVotosDiretor();
		
	}
        
//        SwingUtilities.invokeLater(TelaVotacao::new);
	
//		SwingUtilities.invokeLater(LoginUsuario::new);
//		boolean check = VotanteDAO.verificarsenhavot(senhaDigitada);
	
//			Main programa = new Main();
//			programa.operação();
	
	
	// ____TELAS____
	//	SwingUtilities.invokeLater(() -> new Generica("Diretor"));
	
//	private void mostrarVotantes() {
//		VotanteDAO cd = new VotanteDAO();
//		cd.mostrarVotantes();
//
//	}
}