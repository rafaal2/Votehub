package br.com.votehub.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class ConfirmarVoto {

	private JFrame frame;
	private JPanel panel_verde;
	private boolean lbl1;
	private boolean lbl2;
	private boolean lbl3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfirmarVoto window = new ConfirmarVoto();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConfirmarVoto() {
		try {
			initialize();
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	        frame.setVisible(true);
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao inicializar de Confirmação: " + e.getMessage());
		}
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setTitle("Confirmar Voto");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(new Color(192,192,192));
		frame.getContentPane().setLayout(new MigLayout("fill", "[grow][][][grow][][][20%,grow][][][grow][][][]", "[grow][][][][][grow][][][][][][][][]"));
		
		
		JPanel panelCinza = new JPanel();
		panelCinza.setBackground(new Color(192,192,192));
		panelCinza.setLayout(new MigLayout("fill", "[grow][][][grow][][][][20%,grow][][][grow][][][]", "[grow][][][][][][grow][grow][][][][][][][grow][][]"));
		frame.getContentPane().add(panelCinza, "cell 0 0 13 14,grow");
		
		JLabel lblNewLabel = new JLabel("Confirme Seu Voto");
		panelCinza.add(lblNewLabel, "cell 7 0,alignx 350,aligny center");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		panel_verde = new JPanel();
		panel_verde.setLayout(new MigLayout("fill", "[][][][][][][][]", "[][][][][][][]"));
		panel_verde.setBackground(new Color(164, 247, 176));
		panel_verde.setPreferredSize(new Dimension(900, 300));
		panelCinza.add(panel_verde, "cell 6 1 2 11,grow");
		
		if (AuxGenerica.getNumeroCandidatoTela1() != null) {
			label1(AuxGenerica.getNomeCandidatoTela1(), AuxGenerica.getNumeroCandidatoTela1(), AuxGenerica.getCaminhoImagem1() );
			lbl1 = true;
		}
		if (AuxGenerica.getNumeroCandidatoTela2() != null) {
			if(lbl1 == false) {
				label1(AuxGenerica.getNomeCandidatoTela2(), AuxGenerica.getNumeroCandidatoTela2(), AuxGenerica.getCaminhoImagem2() );
				lbl1 = true;
			}else{
				label2(AuxGenerica.getNomeCandidatoTela2(), AuxGenerica.getNumeroCandidatoTela2(), AuxGenerica.getCaminhoImagem2() );
				lbl2 = true;
			}
		}
		if (AuxGenerica.getNumeroCandidatoTela3() != null) {
			if(lbl1 == false) {
				label1(AuxGenerica.getNomeCandidatoTela3(), AuxGenerica.getNumeroCandidatoTela3(), AuxGenerica.getCaminhoImagem3() );
				lbl1 = true;
			}else if(lbl2 == false) {
				label2(AuxGenerica.getNomeCandidatoTela3(), AuxGenerica.getNumeroCandidatoTela3(), AuxGenerica.getCaminhoImagem3() );
				lbl2 = true;
			}else {
				label3(AuxGenerica.getNomeCandidatoTela3(), AuxGenerica.getNumeroCandidatoTela3(), AuxGenerica.getCaminhoImagem3() );
				lbl3 = true;
			}
		}

		JPanel espaco_can = new JPanel();
		espaco_can.setBackground(new Color(192, 192, 192 ));
		espaco_can.setPreferredSize(new Dimension(240, 0));
		panelCinza.add(espaco_can, "flowx,cell 7 14");
		
		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		botaoCancelar.setPreferredSize(new Dimension(120, 40));
		panelCinza.add(botaoCancelar, "cell 7 14");
		
		JPanel espaco_conf = new JPanel();
		espaco_conf.setBackground(new Color(192, 192, 192 ));
		espaco_conf.setPreferredSize(new Dimension(150, 0));
		panelCinza.add(espaco_conf, "cell 7 14");
		
		JButton botaoConfirmar = new JButton("Confirmar");
		botaoConfirmar.setPreferredSize(new Dimension(120, 40));
		botaoConfirmar.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelCinza.add(botaoConfirmar, "cell 7 14");
		
		botaoConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Voto Efetuado.");
				frame.dispose();
			}
			
		});
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	
	public void label1(String nome, String numero, String foto) {
		ImageIcon cand = new ImageIcon(foto);
		Image candImg = cand.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon resizedCand = new ImageIcon(candImg);	
		JLabel fotoCand1 = new JLabel(resizedCand);
		panel_verde.add(fotoCand1, "cell 1 1,alignx right");
		JLabel numCand1 = new JLabel(numero);
		numCand1.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_verde.add(numCand1, "flowy,cell 2 1");
		JLabel nomCand1 = new JLabel(nome);
		nomCand1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_verde.add(nomCand1, "cell 2 1");
	}
	
	public void label2(String nome, String numero, String foto) {
		ImageIcon cand = new ImageIcon(foto);
		Image candImg = cand.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon resizedCand = new ImageIcon(candImg);	
		JLabel fotoCand2 = new JLabel(resizedCand);
		panel_verde.add(fotoCand2, "cell 1 3,alignx right");
		JLabel numCand2 = new JLabel(numero);
		numCand2.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_verde.add(numCand2, "flowy,cell 2 3");
		JLabel nomCand2 = new JLabel(nome);
		nomCand2.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_verde.add(nomCand2, "cell 2 3");
	}
	
	public void label3(String nome, String numero, String foto) {
		ImageIcon cand = new ImageIcon(foto);
		Image candImg = cand.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon resizedCand = new ImageIcon(candImg);
		JLabel fotoCand3 = new JLabel(resizedCand);
		panel_verde.add(fotoCand3, "cell 1 5,alignx right");
		JLabel numCand3 = new JLabel(numero);
		numCand3.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_verde.add(numCand3, "flowy,cell 2 5");
		JLabel nomCand3 = new JLabel(nome);
		nomCand3.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_verde.add(nomCand3, "cell 2 5");
	}

}
