package br.com.votehub.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.votehub.model.vo.Votante;

public class TelaSelectVotacao extends JFrame {
	private Votante vtt;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	public TelaSelectVotacao(Votante vtt) {
		JPanel panel = new JPanel();
		this.vtt = vtt;
		setTitle("selecionar votação");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 618, 595);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("VOTACÃO");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTitulo.setBounds(267, 11, 131, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblDescricao = new JLabel("escolha qual votaçao votar :");
		lblDescricao.setBounds(76, 104, 184, 43);
		contentPane.add(lblDescricao);
		
		JButton btnVotacaoCandidato = new JButton("candidatos");
		btnVotacaoCandidato.setBounds(283, 88, 89, 23);
		contentPane.add(btnVotacaoCandidato);
		btnVotacaoCandidato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaVotacao telavot = new TelaVotacao(vtt);
				TelaVotacao.setVisible(true);
				dispose();
			}
		});
		
		JButton btnVotacaoProposta = new JButton("propostas");
		btnVotacaoProposta.setBounds(283, 137, 89, 23);
		contentPane.add(btnVotacaoProposta);
		btnVotacaoProposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaProposta telaProp = new TelaProposta();
				telaProp.setVisible(true);
				dispose();
			}
		});
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(283, 253, 89, 23);
		contentPane.add(btnVoltar);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LoginUsuario telalogin = new LoginUsuario();
				telalogin.setVisible(true);
				dispose();
			}
		});

	}
}
