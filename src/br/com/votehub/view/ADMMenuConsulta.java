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
import javax.swing.SwingConstants;

public class ADMMenuConsulta extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ADMMenuConsulta frame = new ADMMenuConsulta();
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
	public ADMMenuConsulta() {
		setTitle("Menu Consulta");
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

		JLabel lblConsulta = new JLabel("Consulta");
		lblConsulta.setFont(new Font("Tahoma", Font.BOLD, 22));
		contentPane.add(lblConsulta, "cell 0 0 7 1,alignx center");
		
		ImageIcon cv = new ImageIcon("./icons/menu_consulta/con_votacao.png");
		Image cvImg = cv.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon resizedCv = new ImageIcon(cvImg);
		JButton btnConsultarVotacao = new JButton("CONSULTAR VOTAÇÃO");
		btnConsultarVotacao.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnConsultarVotacao.setIcon(resizedCv);
		btnConsultarVotacao.setPreferredSize(new Dimension(220, 100));
		btnConsultarVotacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ADMConsultaVotacao consulta;
				try {
					consulta = new ADMConsultaVotacao();
					consulta.setVisible(true);
					dispose();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnConsultarVotacao, "cell 1 1,growx,aligny center");
		
		ImageIcon cc = new ImageIcon("./icons/menu_consulta/con_cand.png");
		Image ccImg = cc.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon resizedCc = new ImageIcon(ccImg);
		JButton btnConsultarCandidato = new JButton("CONSULTAR CANDIDATO");
		btnConsultarCandidato.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnConsultarCandidato.setIcon(resizedCc);
		btnConsultarCandidato.setPreferredSize(new Dimension(220, 100));
		btnConsultarCandidato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ADMConsultaCandidato consulta = new ADMConsultaCandidato();
				consulta.setVisible(true);
				dispose();
			}
		});

		ImageIcon pr = new ImageIcon("./icons/menu_consulta/con_pro.png");
		Image prImg = pr.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon resizedPr = new ImageIcon(prImg);
		JButton btnProposta = new JButton("CONSULTAR PROPOSTA");
		btnProposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ADMConsultaProposta proposta = new ADMConsultaProposta();
				proposta.setVisible(true);
				dispose();
			}
		});

		btnProposta.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnProposta.setIcon(resizedPr);
		btnProposta.setPreferredSize(new Dimension(220, 100));
		panel.add(btnProposta, "cell 1 3,growx,aligny center");
		panel.add(btnConsultarCandidato, "cell 3 1,growx,aligny center");

		ImageIcon ct = new ImageIcon("./icons/menu_consulta/con_vot.png");
		Image ctImg = ct.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon resizedCt = new ImageIcon(ctImg);
		JButton btnConsultaVotante = new JButton("CONSULTAR VOTANTE");
		btnConsultaVotante.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnConsultaVotante.setIcon(resizedCt);
		btnConsultaVotante.setPreferredSize(new Dimension(220, 100));
		btnConsultaVotante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ADMConsultaVotante consulta = new ADMConsultaVotante();
				consulta.setVisible(true);
				dispose();

			}
		});

		panel.add(btnConsultaVotante, "cell 5 1,growx,aligny center");

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

	}

}
