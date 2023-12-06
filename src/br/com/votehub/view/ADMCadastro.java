package br.com.votehub.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class ADMCadastro extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ADMCadastro frame = new ADMCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ADMCadastro() {
		setTitle("Menu Cadastro");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 381);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("fill", "[grow][][][][][][][][][][grow][][grow]", "[][][][][][][][]"));

		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 5 0 1 3,alignx center,aligny center");
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(new MigLayout("fill", "[grow][][][grow][][grow]", "[][][][][][][][][][][][]"));

		JLabel lblCadastro = new JLabel("Cadastro");
		lblCadastro.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblCadastro, "cell 3 0,alignx center,aligny center");

		JButton btnCadEleitor = new JButton("CADASTRAR ELEITOR");
		btnCadEleitor.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCadEleitor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ADMCadEleitor admCadEleitor = new ADMCadEleitor();
				admCadEleitor.setVisible(true);
				dispose();
			}
		});

		JButton btnCadCandidato = new JButton("CADASTRAR CANDIDATO");
		btnCadCandidato.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCadCandidato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ADMCadCandidato admCadCandidato = new ADMCadCandidato();
				admCadCandidato.setVisible(true);
				dispose();
			}
		});

		JButton btnCadEleicao = new JButton("CADASTRAR ELEIÇÃO");
        btnCadEleicao.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnCadEleicao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

				CADEleicao cadEleicao;
				try {
					cadEleicao = new CADEleicao();
					cadEleicao.setVisible(true);
					dispose();
				} catch (ParseException e1) {					
					e1.printStackTrace();
				}
//				cadEleicao.setVisible(true);
//				dispose();

			}
		});

		panel.add(btnCadCandidato, "cell 3 2,growx,aligny center");
		panel.add(btnCadEleicao, "cell 3 3,growx,aligny center");
		panel.add(btnCadEleitor, "cell 3 4,growx,aligny center");

		JButton btnCadVoltar = new JButton("VOLTAR");
		btnCadVoltar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCadVoltar.addActionListener(new ActionListener() {

	public void actionPerformed(ActionEvent e) {

				ADMPrincipal admPrincipal = new ADMPrincipal();
				admPrincipal.setVisible(true);
				dispose();
			}
		});

		JButton btnCadProposta = new JButton("CADASTRAR PROPOSTA");
		btnCadProposta.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnCadProposta, "cell 3 5,growx,aligny center");
		panel.add(btnCadVoltar, "cell 3 11,alignx center,aligny center");
		btnCadProposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ADMCadProposta cadproposta = new ADMCadProposta();
				cadproposta.setVisible(true);
				dispose();
			}
		});
	}

}
