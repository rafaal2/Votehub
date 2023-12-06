package br.com.votehub.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class TelaProposta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaProposta frame = new TelaProposta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaProposta() {
		setTitle("votação de proposta");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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
		
		JButton btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.setBounds(689, 547, 100, 30);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConfirmar.setToolTipText("");
		panel.add(btnConfirmar);
		
		JLabel lblTextProposta = new JLabel("PROPOSTAS ABERTAS:");
		lblTextProposta.setBounds(260, 169, 130, 30);
		panel.add(lblTextProposta);
		
		JLabel lblTextResposta = new JLabel("RESPOSTA:");
		lblTextResposta.setBounds(276, 375, 100, 30);
		panel.add(lblTextResposta);
		
		JComboBox comboBoxPropostas = new JComboBox();
		comboBoxPropostas.setBounds(427, 173, 150, 30);
		panel.add(comboBoxPropostas);
		
		JComboBox comboBoxResposta = new JComboBox();
		comboBoxResposta.setBounds(427, 379, 150, 30);
		panel.add(comboBoxResposta);
		
		JButton btnDescricao = new JButton("Descrição da Proposta");
		btnDescricao.setBounds(427, 260, 150, 23);
		panel.add(btnDescricao);
	}
}
