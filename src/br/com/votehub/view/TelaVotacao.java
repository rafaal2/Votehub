package br.com.votehub.view;

import javax.swing.JFrame;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

public class TelaVotacao {

	private JFrame frame;
	private JTextField textField;
	private JLabel lblNewLabel_2;
	String ano = Integer.toString(LocalDate.now().getYear());
	private JButton btnNewButton_2;
	private JButton btnNewButton_1;
	private JRadioButton rdbtnNewRadioButton;

	// CHAMANDO A TELA -> SwingUtilities.invokeLater(TelaVotacao::new);
	
	public TelaVotacao() {
		try {
			initialize();
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setVisible(true);
      } catch (Exception e) {
          e.printStackTrace();
          JOptionPane.showMessageDialog(null, "Erro ao inicializar a Tela de Votação: " + e.getMessage());
      }
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("fill", "[][][grow][][][20%][][][grow][][]", "[][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Eleição "+ ano);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		frame.getContentPane().add(lblNewLabel, "cell 5 1,alignx center,aligny bottom");
		
		JLabel lblNewLabel_1 = new JLabel("Cargo: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(lblNewLabel_1, "cell 5 2,alignx center,aligny bottom");
		
		lblNewLabel_2 = new JLabel("Nº:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		frame.getContentPane().add(lblNewLabel_2, "cell 4 3,alignx right,aligny center");
		
		textField = new JTextField();
		frame.getContentPane().add(textField, "flowx,cell 5 3,growx,aligny center");
		textField.setColumns(10);
		
		btnNewButton_2 = new JButton("  Cancelar");
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().add(btnNewButton_2, "flowx,cell 5 4,alignx center,aligny baseline");
		
		btnNewButton_1 = new JButton("Avançar");
		btnNewButton_1.setHorizontalAlignment(SwingConstants.RIGHT);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		rdbtnNewRadioButton = new JRadioButton("");
		rdbtnNewRadioButton.setEnabled(false);
		rdbtnNewRadioButton.setVisible(false);
		frame.getContentPane().add(rdbtnNewRadioButton, "cell 5 4");
		frame.getContentPane().add(btnNewButton_1, "cell 5 4,alignx center,aligny baseline");
	}

}
