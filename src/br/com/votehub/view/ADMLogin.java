package br.com.votehub.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerAdm;
import net.miginfocom.swing.MigLayout;

public class ADMLogin extends JFrame {

	private JPanel contentPane;
	private JTextField fieldUserLogin;
	private JTextField fieldSenhaLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ADMLogin frame = new ADMLogin();
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
	public ADMLogin() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("fill", "[grow][][grow][][grow]", "[][][][][][][][]"));

		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 2 0,alignx center,growy");
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(new MigLayout("fill", "[grow][][grow][][][][grow]", "[][][][][][][][][]"));

		JLabel lblVoteHub = new JLabel("VoteHub");
		lblVoteHub.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblVoteHub, "cell 0 0 7 1,alignx center,aligny center");

		JLabel lblUserLogin = new JLabel("Usuário");
		lblUserLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(lblUserLogin, "cell 0 2 2 1,alignx trailing,aligny center");

		fieldUserLogin = new JTextField();
		panel.add(fieldUserLogin, "cell 2 2 4 1,growx");
		fieldUserLogin.setColumns(10);

		JLabel lblSenhaLogin = new JLabel("Senha");
		lblSenhaLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(lblSenhaLogin, "cell 0 4 2 1,alignx trailing,aligny center");

		fieldSenhaLogin = new JTextField();
		panel.add(fieldSenhaLogin, "cell 2 4 4 1,growx");

		JButton btnEntrarLogin = new JButton("Entrar");
		panel.add(btnEntrarLogin, "cell 0 6 7 1,alignx center,aligny center");
		btnEntrarLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String logindigit = fieldUserLogin.getText();
				String senhadigit = fieldSenhaLogin.getText();
				try {
					ControllerAdm contAdm = new ControllerAdm();
					contAdm.verificarloginadm(logindigit);
					contAdm.verificarsenhaadm(senhadigit);
					ADMPrincipal admprincipal = new ADMPrincipal();
	                admprincipal.setVisible(true);
	                dispose();
				} catch (BusinessException error) {
					JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 

			}
		});
	

		JButton btnVoltar = new JButton("Voltar");
		panel.add(btnVoltar, "cell 0 8");
		btnVoltar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				TelaInicial telaInicio = new TelaInicial();
				telaInicio.setVisible(true);
				dispose();
			}
		});
	}
}
