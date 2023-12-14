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
import net.miginfocom.swing.MigLayout;

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
		contentPane.setLayout(new MigLayout("fill", "[][grow][][grow][][grow][]", "[][][][][][][][]"));
		
		JLabel lblTitulo = new JLabel("VOTACÃO");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 17));
		contentPane.add(lblTitulo, "cell 3 0,alignx center,growy");
		
		JLabel lblDescricao = new JLabel("Escolha o tipo de votação:");
		lblDescricao.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblDescricao, "cell 3 1,alignx center,aligny center");
		
		JButton btnVotacaoCandidato = new JButton("Candidatos");
		btnVotacaoCandidato.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(btnVotacaoCandidato, "cell 3 2,grow");
		btnVotacaoCandidato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaVotacao telavot = new TelaVotacao(vtt);
				TelaVotacao.setVisible(true);
				dispose();	
				//AuxGenerica.setIdVotante(vtt);
			//VerificarEleicoes verificarEleicoes = new VerificarEleicoes();
			//verificarEleicoes.votacaoAbertaCandidatos();
			//dispose();

			}
		});
		
		JButton btnVotacaoProposta = new JButton("Propostas");
		btnVotacaoProposta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(btnVotacaoProposta, "cell 3 4,grow");
		btnVotacaoProposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaProposta telaProp = new TelaProposta(vtt);
				telaProp.setVisible(true);
				dispose();
			}
		});
		
		JButton btnVoltar = new JButton("VOLTAR");
		contentPane.add(btnVoltar, "cell 3 6,alignx center,aligny center");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaLoginVotante telalogin = new TelaLoginVotante();
				telalogin.setVisible(true);
				dispose();
			}
		});

	}
}
