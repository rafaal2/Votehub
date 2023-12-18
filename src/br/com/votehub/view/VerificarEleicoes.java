package br.com.votehub.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import br.com.votehub.*;
import br.com.votehub.model.DAOs.DB;
import br.com.votehub.model.vo.Votante;

public class VerificarEleicoes {

	private int idVotacao;
	private int idProposta;
	private Votante vtt;
	Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
	
	
	public void votacaoAbertaCandidatos(Votante vtt) {
		try {
			boolean vot = false;
            conn = DB.getConnection();
            String query = "SELECT data_inicio, data_fim, id_votacao \r\n FROM votacao \r\n WHERE tipo_votacao = 'candidatos'";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
            	Timestamp dataInicio = rs.getTimestamp("data_inicio");
                Timestamp dataFim = rs.getTimestamp("data_fim");
                idVotacao = rs.getInt("id_votacao");
                Date dataAtual = new Date();
                boolean andamento = dataAtual.after(dataInicio) && dataAtual.before(dataFim);
                if(andamento) {
//                	AuxGenerica auxGenerica = new AuxGenerica();
//                	auxGenerica.obterCargos(idVotacao);
                	vot = true;
                	TelaVotacao2 telavot = new TelaVotacao2(vtt, idVotacao);
					TelaVotacao2.setVisible(true);
                	break;
                }
            }
           if(!vot) {
               JOptionPane.showMessageDialog(null, "Não há votação de candidatos em andamento.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
           }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
		
	}
	
	public void votacaoAbertaPropostas() {
		try {
            conn = DB.getConnection();
            String query = "SELECT data_inicio, data_fim \r\n FROM votacao \r\n WHERE tipo_eleicao = propostas";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
            	Timestamp dataInicio = rs.getTimestamp("data_inicio");
                Timestamp dataFim = rs.getTimestamp("data_fim");
                idProposta = rs.getInt("id_votacao");
                Date dataAtual = new Date();
                boolean andamento = dataAtual.after(dataInicio) && dataAtual.before(dataFim);
                if(andamento) {
                	
                	break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
	}
}
