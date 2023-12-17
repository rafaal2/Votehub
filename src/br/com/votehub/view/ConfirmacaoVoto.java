package br.com.votehub.view;

import javax.swing.JFrame;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Container;
import java.awt.Font;
import java.awt.Image;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ConfirmacaoVoto {

	private JFrame frame;
	private boolean lbl1;
	private boolean lbl2;
	private boolean lbl3;
	String ano = Integer.toString(LocalDate.now().getYear());
	
	// CHAMANDO A TELA -> SwingUtilities.invokeLater(ConfirmacaoVoto::new);
	
	public ConfirmacaoVoto() {
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
		frame.getContentPane().setLayout(new MigLayout("fill", "[][][][grow][][][20%,grow][][][grow][][][]", "[][][][][][][][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Confirme Seu Voto");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		frame.getContentPane().add(lblNewLabel, "cell 6 1,alignx center,aligny bottom");
		
		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		
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
		frame.getContentPane().add(botaoCancelar, "flowx,cell 6 12,alignx center,aligny center");
		
		JButton botaoConfirmar = new JButton("Confirmar");
		
		botaoConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Voto Efetuado.");
				SwingUtilities.invokeLater(TelaLoginVotante::new);
				frame.dispose();
			}
			
		});
		frame.getContentPane().add(botaoConfirmar, "cell 6 12,alignx center,aligny center");
	}

	public void label1(String nome, String numero, String foto) {
		ImageIcon cand = new ImageIcon(foto);
		Image candImg = cand.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon resizedCand = new ImageIcon(candImg);
		JLabel fotoCand1 = new JLabel(resizedCand);
		frame.getContentPane().add(fotoCand1, "cell 5 3,aligny center");
		JLabel numCand1 = new JLabel(numero);
		numCand1.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(numCand1, "flowy,cell 6 3");
		JLabel nomCand1 = new JLabel(nome);
		frame.getContentPane().add(nomCand1, "cell 6 3,aligny top");
	}
	
	public void label2(String nome, String numero, String foto) {
		ImageIcon cand = new ImageIcon(foto);
		Image candImg = cand.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon resizedCand = new ImageIcon(candImg);
		JLabel fotoCand2 = new JLabel(resizedCand);
		frame.getContentPane().add(fotoCand2, "cell 5 6");
		JLabel numCand2 = new JLabel(numero);
		numCand2.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(numCand2, "flowy,cell 6 6");
		JLabel nomCand2 = new JLabel(nome);
		frame.getContentPane().add(nomCand2, "cell 6 6");
		
	}
	
	public void label3(String nome, String numero, String foto) {
		ImageIcon cand = new ImageIcon(foto);
		Image candImg = cand.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon resizedCand = new ImageIcon(candImg);
		JLabel fotoCand3 = new JLabel(resizedCand);
		frame.getContentPane().add(fotoCand3, "cell 5 9");
		JLabel numCand3 = new JLabel(numero);
		numCand3.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(numCand3, "flowy,cell 6 9");
		JLabel nomCand3 = new JLabel(nome);
		frame.getContentPane().add(nomCand3, "cell 6 9");
	}
}
