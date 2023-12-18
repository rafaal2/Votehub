package br.com.votehub.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerAdm;
import br.com.votehub.model.DAOs.DbException;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLayeredPane;

public class ADMLogin extends JFrame {

	private JPanel contentPane;
	private JTextField fieldUserLogin;
	private JPasswordField fieldSenhaLogin;

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
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(164, 247, 176));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("fill", "[][][grow][][grow][][]", "[][][][][][][][][][][][][]"));

		ImageIcon user = new ImageIcon("./icons/adm.png");
		Image AdmImg = user.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
		ImageIcon resizedAdm = new ImageIcon(AdmImg);

		JLabel admIcon = new JLabel(resizedAdm);
		contentPane.add(admIcon, "cell 3 1,alignx center");

		JLabel lblVoteHub = new JLabel("Administrador");
		contentPane.add(lblVoteHub, "cell 3 3,alignx center");
		lblVoteHub.setFont(new Font("Tahoma", Font.BOLD, 17));

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(panel, "cell 3 6,alignx trailing,growy");
		panel.setPreferredSize(new Dimension(400, 250));
		panel.setBackground(SystemColor.menu);
		panel.setLayout(new MigLayout("fill", "[][][][][][][]", "[][][][][]"));

		JLabel lblUserLogin = new JLabel("Usuário :");
		lblUserLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(lblUserLogin, "cell 0 1,alignx trailing,aligny center");

		fieldUserLogin = new JTextField();
		panel.add(fieldUserLogin, "cell 1 1 5 1,growx");
		fieldUserLogin.setColumns(10);

		JLabel lblSenhaLogin = new JLabel("Senha :");
		lblSenhaLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(lblSenhaLogin, "cell 0 3,alignx trailing,aligny center");

		fieldSenhaLogin = new JPasswordField();
		panel.add(fieldSenhaLogin, "cell 1 3 5 1,growx");

		JButton btnVoltar = new JButton("VOLTAR");
		contentPane.add(btnVoltar, "flowx,cell 3 8,alignx center");
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVoltar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				TelaInicial telaInicio = new TelaInicial();
				telaInicio.setVisible(true);
				dispose();
			}
		});
		// Separação entre botoes Entrar e Voltar
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(120, 0));
		panel_1.setBackground(new Color(164, 247, 176));
		contentPane.add(panel_1, "cell 3 8");

		JButton btnEntrarLogin = new JButton("ENTRAR");
		contentPane.add(btnEntrarLogin, "cell 3 8,alignx center");
		btnEntrarLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEntrarLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String loginDigitada = fieldUserLogin.getText();
				String senhaDigitada = fieldSenhaLogin.getText();
				try {
					ControllerAdm contAdm = new ControllerAdm();
					contAdm.verificarloginadm(loginDigitada);
					contAdm.verificarsenhaadm(senhaDigitada);
					ADMPrincipal admprincipal = new ADMPrincipal();
					admprincipal.setVisible(true);
					dispose();
				} catch (BusinessException error) {
					JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (DbException e1) {
					if (e1.getCause() instanceof SQLIntegrityConstraintViolationException) {
						JOptionPane.showMessageDialog(null, "erro do sistema", "Erro do sistema",
								JOptionPane.ERROR_MESSAGE);
					} else if (e1.getCause() instanceof CommunicationsException) {
						JOptionPane.showMessageDialog(null, "erro do sistema", "Erro do sistema",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "erro do sistema", "Erro do sistema",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
}
