package br.com.votehub.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	/**
	 * Create the frame.
	 */
	public ADMCadastro() {
		setTitle("Menu Cadastro");
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
		panel.setLayout(new MigLayout("fill", "[grow][][][grow][][grow]", "[][][][][][][][][][][]"));
		
		JLabel lblCadastro = new JLabel("Cadastro");
		lblCadastro.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblCadastro, "cell 3 0,alignx center,aligny center");
		
		JButton btnCadEleitor = new JButton("CAD. ELEITOR");
		btnCadEleitor.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCadEleitor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnCadCandidato = new JButton("CAD. CANDIDATO");
		btnCadCandidato.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnCadCandidato, "cell 3 2,growx,aligny center");
		panel.add(btnCadEleitor, "cell 3 3,growx,aligny center");
		
		JButton btnCadVoltar = new JButton("VOLTAR");
		btnCadVoltar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCadVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnCadVoltar, "cell 3 10,alignx center,aligny center");
	}

}
