package br.com.votehub.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
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
		contentPane.setBackground(new Color(164, 247, 176));
		contentPane.setBorder(new EmptyBorder(5, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("fill", "[][][][][][][]", "[][][][][][][][][]"));

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panel, "cell 0 1 7 8,grow");
		panel.setLayout(new MigLayout("fill", "[grow][][grow][][grow][][grow]", "[][][][][][][]"));

		JLabel lblCadastro = new JLabel("Cadastro");
		lblCadastro.setFont(new Font("Tahoma", Font.BOLD, 22));
		contentPane.add(lblCadastro, "cell 0 0 7 1,alignx center");

		ImageIcon vt = new ImageIcon("./icons/menu_cadastro/cad_vot.png");
		Image vtImg = vt.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon resizedVt = new ImageIcon(vtImg);
		JButton btnCadVotante = new JButton("CADASTRAR VOTANTE");
		btnCadVotante.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCadVotante.setIcon(resizedVt);
		btnCadVotante.setPreferredSize(new Dimension(220, 100));
		btnCadVotante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ADMCadVotante admCadVotante = new ADMCadVotante();
				admCadVotante.setVisible(true);
				dispose();
			}
		});
		panel.add(btnCadVotante, "cell 1 1,growx,aligny center");

		ImageIcon ca = new ImageIcon("./icons/menu_cadastro/cad_cand.png");
		Image caImg = ca.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon resizedCa = new ImageIcon(caImg);
		JButton btnCadCandidato = new JButton("CADASTRAR CANDIDATO");
		btnCadCandidato.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCadCandidato.setIcon(resizedCa);
		btnCadCandidato.setPreferredSize(new Dimension(220, 100));
		btnCadCandidato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ADMCadCandidato admCadCandidato = new ADMCadCandidato();
				admCadCandidato.setVisible(true);
				dispose();
			}
		});
		panel.add(btnCadCandidato, "cell 3 1,growx,aligny center");

		ImageIcon cv = new ImageIcon("./icons/menu_cadastro/cad_votacao.png");
		Image cvImg = cv.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon resizedCv = new ImageIcon(cvImg);
		JButton btnCadVotacao = new JButton("CADASTRAR VOTAÇÃO");
		btnCadVotacao.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCadVotacao.setIcon(resizedCv);
		btnCadVotacao.setPreferredSize(new Dimension(220, 100));
		btnCadVotacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ADMCadVotacao cadVotacao;
				try {
					cadVotacao = new ADMCadVotacao();
					cadVotacao.setVisible(true);
					dispose();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
//				cadVotacao.setVisible(true);
//				dispose();

			}
		});
		panel.add(btnCadVotacao, "cell 5 1,growx,aligny center");

		JButton btnCadVoltar = new JButton("VOLTAR");
		btnCadVoltar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCadVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ADMPrincipal admPrincipal = new ADMPrincipal();
				admPrincipal.setVisible(true);
				dispose();
			}
		});
		panel.add(btnCadVoltar, "cell 1 5,alignx center,aligny center");

		ImageIcon pp = new ImageIcon("./icons/menu_consulta/con_votacao.png");
		Image ppImg = pp.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon resizedPp = new ImageIcon(ppImg);
		JButton btnCadProposta = new JButton("CADASTRAR PROPOSTA");
		btnCadProposta.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCadProposta.setIcon(resizedPp);
		btnCadProposta.setPreferredSize(new Dimension(220, 100));
		btnCadProposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ADMCadProposta cadproposta = new ADMCadProposta();
				cadproposta.setVisible(true);
				dispose();
			}
		});
		panel.add(btnCadProposta, "cell 1 3,growx,aligny center");

//		JButton espaco = new JButton("CONSULTAR VOTANTE");
//		espaco.setPreferredSize(new Dimension(220, 100));
//		panel.add(espaco, "cell 1 3,growx,aligny center");
//		espaco.setVisible(false);
	}

}
