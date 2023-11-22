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
import javax.swing.JComboBox;

public class TelaVotacao {

	private JFrame frame;
	String ano = Integer.toString(LocalDate.now().getYear());
	private JButton botaoCancelar;
	private JButton botaoAvancar;
	private JComboBox comboBox;

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
		frame.getContentPane().setLayout(new MigLayout("fill", "[][][grow][][][20%,grow][][][grow][][]", "[][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Eleição "+ ano);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		frame.getContentPane().add(lblNewLabel, "cell 5 1,alignx center,aligny bottom");
		
		JLabel lblNewLabel_1 = new JLabel("Cargo: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(lblNewLabel_1, "cell 5 2,alignx center,aligny bottom");
		
		botaoCancelar = new JButton("  Cancelar");
		botaoCancelar.setHorizontalAlignment(SwingConstants.LEFT);
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		comboBox = new JComboBox();
		frame.getContentPane().add(comboBox, "cell 5 3,growx");
		frame.getContentPane().add(botaoCancelar, "flowx,cell 5 4,alignx center,aligny baseline");
		
		botaoAvancar = new JButton("Avançar");
		botaoAvancar.setHorizontalAlignment(SwingConstants.RIGHT);
		botaoAvancar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().add(botaoAvancar, "cell 5 4,alignx center,aligny baseline");
	}

}
