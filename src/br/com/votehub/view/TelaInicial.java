package br.com.votehub.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaInicial() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("fill", "[][][][][][grow][][][][][]", "[][][][][][][][][][][][]"));

		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 4 0 2 1,alignx center,growy");
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(new MigLayout("fill", "[grow][][grow][][][][][grow]", "[][][][][][][][][][][]"));

		JLabel lblNewLabelVotehub = new JLabel("VoteHub");
		panel.add(lblNewLabelVotehub, "cell 1 0 5 1,alignx center,aligny center");
		lblNewLabelVotehub.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabelVotehub.setFont(new Font("Tahoma", Font.BOLD, 34));

		JButton btnNewButtonAcessoUsuario = new JButton("USUÁRIO");
		panel.add(btnNewButtonAcessoUsuario, "cell 1 3 5 1,alignx center,aligny center");
		btnNewButtonAcessoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginUsuario loginUsuario = new LoginUsuario();
				loginUsuario.setVisible(true);
				dispose();
			}
		});
		btnNewButtonAcessoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 30));

		JButton btnNewButtonAcessoAdm = new JButton("ADMINISTRADOR");
		panel.add(btnNewButtonAcessoAdm, "cell 2 5 4 1,alignx center,aligny center");
		btnNewButtonAcessoAdm.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButtonAcessoAdm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ADMLogin admLogin = new ADMLogin();
				admLogin.setVisible(true);
				dispose();
			}
		});
	}
}
