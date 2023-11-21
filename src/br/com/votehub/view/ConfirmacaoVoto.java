package br.com.votehub.view;

import javax.swing.JFrame;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import net.miginfocom.swing.MigLayout;

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
		frame.setTitle("Confirmar Voto");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("fill", "[grow][][grow][][grow]", "[][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Eleição " + ano);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		frame.getContentPane().add(lblNewLabel, "cell 2 1,alignx center,aligny center");
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		frame.getContentPane().add(lblNewLabel_1, "cell 2 2,alignx center,aligny center");
	}

}
