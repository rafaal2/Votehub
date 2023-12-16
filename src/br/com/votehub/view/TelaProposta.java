package br.com.votehub.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerProposta;
import br.com.votehub.controller.ControllerPropostaVotante;
import br.com.votehub.controller.ControllerRespostaProposta;
import br.com.votehub.model.vo.Votante;

public class TelaProposta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Votante vtt;
	private JComboBox<String> comboBoxPropostas;
	private ButtonGroup buttonGroup;
	private JRadioButton[] radioButtons;

	public TelaProposta(Votante vtt) {
		TelaProposta.vtt = vtt;
		setTitle("votação de proposta");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(164, 247, 176));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 5 0 1 3,alignx center,aligny center");
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(null);

		JLabel lblCadCandidato = new JLabel("Votação de Proposta");
		lblCadCandidato.setBounds(312, 31, 177, 21);
		lblCadCandidato.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblCadCandidato);

		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(51, 547, 100, 30);
		panel.add(btnVoltar);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaSelectVotacao admPrincipal = new TelaSelectVotacao(vtt);
				admPrincipal.setVisible(true);
				dispose();
			}
		});

		JLabel lblTextProposta = new JLabel("PROPOSTAS ABERTAS:");
		lblTextProposta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTextProposta.setBounds(222, 169, 168, 30);
		panel.add(lblTextProposta);

		JLabel lblTextResposta = new JLabel("RESPOSTA:");
		lblTextResposta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTextResposta.setBounds(276, 375, 100, 30);
		panel.add(lblTextResposta);

		comboBoxPropostas = new JComboBox();
		comboBoxPropostas.setBounds(427, 173, 150, 30);
		panel.add(comboBoxPropostas);
		restaurarTituloCombobox();
		
		 buttonGroup = new ButtonGroup();
	        radioButtons = new JRadioButton[5]; 

	        for (int i = 0; i < radioButtons.length; i++) {
	            radioButtons[i] = new JRadioButton(obterTextoOpcao(i));
	            radioButtons[i].setBounds(427, 379 + i * 30, 150, 30);
	            panel.add(radioButtons[i]);
	            buttonGroup.add(radioButtons[i]);
	        }


		JButton btnDescricao = new JButton("Descrição da Proposta");
		btnDescricao.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnDescricao.setBounds(427, 260, 150, 23);
		panel.add(btnDescricao);
		btnDescricao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tituloSelecionado = (String) comboBoxPropostas.getSelectedItem();
				if (tituloSelecionado != null) {
					try {
						exibirDescricaoProposta(tituloSelecionado);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		JButton btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.setBounds(689, 547, 100, 30);
		btnConfirmar.setToolTipText("");
		panel.add(btnConfirmar);
		btnConfirmar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String tituloSelecionado = (String) comboBoxPropostas.getSelectedItem();
		        ControllerProposta contProposta = new ControllerProposta();
		        
		        // Utilize o método obterRespostaSelecionada para obter a resposta selecionada
		        String resposta = obterRespostaSelecionada();
		        
		        int idProposta = 0;
		        try {
		            idProposta = contProposta.obterIdPorTitulo(tituloSelecionado);
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
		        
		        try {
		            ControllerPropostaVotante contpv = new ControllerPropostaVotante();
		            contpv.checarRespostaUnica(vtt.getId_votante(), idProposta);
		            contpv.adicionarPropostaVotante(vtt.getId_votante(), idProposta);

		            ControllerRespostaProposta contVoto = new ControllerRespostaProposta();
		            contVoto.registrarRespostaProposta(resposta, idProposta);

		            JOptionPane.showMessageDialog(null, "Resposta cadastrada com sucesso!");
		        } catch (BusinessException error) {
		            JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		        } catch (SQLException error) {
		            JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		            // e1.printStackTrace();
		        }
		    }
		});
	}
	public void restaurarTituloCombobox() {
		try {
			ControllerProposta objProposta = new ControllerProposta();
			ResultSet rs = objProposta.exibirTitulo();
			while (rs.next()) {
				this.comboBoxPropostas.addItem(rs.getString("titulo"));
			}
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void exibirDescricaoProposta(String tituloProposta) throws SQLException {
		ControllerProposta objProposta = new ControllerProposta();
		String descricao = objProposta.obterDescricaoPorTitulo(tituloProposta);

		if (descricao != null) {
			descricao = descricao.replace("\\n", "<br>");
			String htmlDesc = "<html><body style='width: 300px;'>" + descricao + "</body></html>";
			JOptionPane.showMessageDialog(this, htmlDesc, "Descrição da Proposta", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Descrição não encontrada para a proposta selecionada.", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private String obterTextoOpcao(int indice) {
		switch (indice) {
		case 0:
			return "Sim";
		case 1:
			return "Provavelmente sim";
		case 2:
			return "Talvez";
		case 3:
			return "Provavelmente não";
		case 4:
			return "Não";
		default:
			return "";
		}
	}

	private String obterRespostaSelecionada() {
		for (int i = 0; i < radioButtons.length; i++) {
			if (radioButtons[i].isSelected()) {
				return obterTextoOpcao(i);
			}
		}
		return "";
	}
}
