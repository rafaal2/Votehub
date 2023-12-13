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
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("fill", "[grow][][][][][][][][][][grow][][grow]", "[][][][][][][][]"));

		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 5 0 1 3,alignx center,aligny center");
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(
				new MigLayout("fill", "[grow][][][grow][][grow]", "[][][][][][][][][][][][][][][][][][][][][][]"));

		JLabel lblConsulta = new JLabel("Consulta");
		lblConsulta.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblConsulta, "cell 3 0,alignx center,aligny center");

		JButton btnCadVoltar = new JButton("VOLTAR");
		btnCadVoltar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCadVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ADMPrincipal admPrincipal = new ADMPrincipal();
				admPrincipal.setVisible(true);
				dispose();
			}
		});

		JButton btnConsultaVotante = new JButton("CONSULTAR VOTANTE");
		btnConsultaVotante.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnConsultaVotante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ADMConsultaVotante consulta = new ADMConsultaVotante();
				consulta.setVisible(true);
				dispose();

			}
		});

		panel.add(btnConsultaVotante, "cell 3 3,growx,aligny center");

		JButton btnConsultarCandidato = new JButton("CONSULTAR CANDIDATO");
		btnConsultarCandidato.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnConsultarCandidato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ADMConsultaCandidato consulta = new ADMConsultaCandidato();
				consulta.setVisible(true);
				dispose();
			}
		});
		panel.add(btnConsultarCandidato, "cell 3 6,growx,aligny center");

		JButton btnConsultarVotacao = new JButton("CONSULTAR VOTAÇÃO");
		btnConsultarVotacao.setFont(new Font("Tahoma", Font.BOLD, 12));
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
		panel.add(btnConsultarVotacao, "cell 3 10,growx,aligny center");

		JButton btnProposta = new JButton("CONSULTAR PROPOSTA");
		btnProposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ADMConsultaProposta proposta = new ADMConsultaProposta();
				proposta.setVisible(true);
				dispose();
			}
		});

		btnProposta.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnProposta, "cell 3 14,growx,aligny center");
		panel.add(btnCadVoltar, "cell 3 21,alignx center,aligny center");

	}

}
