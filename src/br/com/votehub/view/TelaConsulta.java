package br.com.votehub.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;

public class TelaConsulta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsulta frame = new TelaConsulta();
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
	public TelaConsulta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][grow][]", "[][][][][][][][][grow][][][][][grow]"));
		
		JButton btnVoltarMenu = new JButton("VOLTAR");
		btnVoltarMenu.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(btnVoltarMenu, "cell 0 0");
		
		JButton btnNewButton_1 = new JButton("Exibir Votantes");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(btnNewButton_1, "cell 1 2 2 1,alignx center,growy");
		
		JButton btnNewButton_2 = new JButton("Exibir Candidatos");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(btnNewButton_2, "cell 1 3 2 1,alignx center,growy");
		
		JButton btnNewButton_3 = new JButton("Apurar Votos");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(btnNewButton_3, "cell 1 4 2 1,alignx center,growy");
		
		JTextArea textArea = new JTextArea();
		textArea.setTabSize(40);
		textArea.setWrapStyleWord(true);
		textArea.setRows(5);
		textArea.setColumns(40);
		contentPane.add(textArea, "cell 2 8,alignx center,growy");
	}

}
