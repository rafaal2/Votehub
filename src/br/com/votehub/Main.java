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
	}
        
}