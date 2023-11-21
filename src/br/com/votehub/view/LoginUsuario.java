package br.com.votehub.view;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

public class LoginUsuario {

	private JFrame frame;
	private JTextField campoMatricula;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	String ano = Integer.toString(LocalDate.now().getYear());
	private JTextField campoCpf;
	
	// CHAMANDO A TELA -> SwingUtilities.invokeLater(LoginUsuario::new);
	
	public LoginUsuario() {
		try {
			initialize();
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	        frame.setVisible(true);
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao inicializar a Tela de Login de Usuário: " + e.getMessage());
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Login Usuário");
		//frame.setSize(1366, 768);//eixo x e eixo y
		//frame.resizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("fill", "[grow][][grow][][grow]", "[][][][][][][][]"));
		
		lblNewLabel_2 = new JLabel("Eleição " + ano);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		frame.getContentPane().add(lblNewLabel_2, "cell 2 2,alignx center");
		
		lblNewLabel = new JLabel("CPF:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		frame.getContentPane().add(lblNewLabel, "cell 1 3,alignx trailing");
		
		campoCpf = new JTextField();
		frame.getContentPane().add(campoCpf, "cell 2 3,growx");
		campoCpf.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Matrícula:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(lblNewLabel_1, "cell 1 4,alignx trailing");
		
		campoMatricula = new JTextField();
		frame.getContentPane().add(campoMatricula, "cell 2 4,growx");
		campoMatricula.setColumns(10);
		
		// AÇÃO BOTÃO ENTRAR
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent entrar) {
				String cpf, matricula;
				try {
					cpf = campoCpf.getText();
					matricula = campoMatricula.getText();
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Usuário Inválido");
				}
			}
		});
		frame.getContentPane().add(btnNewButton, "cell 2 5,alignx center");

		
	}

}
