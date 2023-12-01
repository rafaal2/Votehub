package br.com.votehub.view;

import javax.swing.JFrame;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ConfirmacaoVoto {

	private JFrame frame;
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
		frame.getContentPane().setLayout(new MigLayout("fill", "[][][grow][][][20%,grow][][][grow][][]", "[][][][grow][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Eleição " + ano);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		frame.getContentPane().add(lblNewLabel, "cell 5 2,alignx center,aligny center");
		
		JLabel lblNewLabel_1 = new JLabel("dados candidato");
		frame.getContentPane().add(lblNewLabel_1, "cell 5 3");
		
		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				SwingUtilities.invokeLater((Runnable) new TelaVotacao(null));
			}
		});
		frame.getContentPane().add(botaoCancelar, "flowx,cell 5 4,alignx center,aligny center");
		
		JButton botaoConfirmar = new JButton("Confirmar");
		
		botaoConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Voto Efetuado.");
				SwingUtilities.invokeLater(LoginUsuario::new);
				frame.dispose();
			}
			
		});
		frame.getContentPane().add(botaoConfirmar, "cell 5 4,alignx center,aligny center");
		
	}

}
