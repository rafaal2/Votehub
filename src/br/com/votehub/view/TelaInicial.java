package br.com.votehub.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class TelaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		

		ImageIcon icon = new ImageIcon("./icons/cedula.png");
		ImageIcon adm = new ImageIcon("./icons/adm.png");
		ImageIcon user = new ImageIcon("./icons/user.png");

		// Resize the image
		Image image = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		Image admImg = adm.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		Image userImg = user.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(image);
		ImageIcon resizedAdm = new ImageIcon(admImg);
		ImageIcon resizedUser = new ImageIcon(userImg);
		getContentPane().setLayout(new MigLayout("fill", "[409px][383px]", "[487px]"));

		JPanel panelEsquerda = new JPanel();
		panelEsquerda.setBackground(new Color(192, 192, 192));
		getContentPane().add(panelEsquerda, "cell 0 0,grow");
		panelEsquerda.setLayout(new MigLayout("fill", "[46px][][][][][][][][][][][][][][][]", "[14px][][][][][][]"));

		JLabel lblIcone = new JLabel(resizedIcon);
		lblIcone.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panelEsquerda.add(lblIcone, "cell 8 3,alignx left,aligny top");

		JLabel lblTitulo = new JLabel(" VOTEHUB");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelEsquerda.add(lblTitulo, "cell 8 4");

		JPanel panelDireita = new JPanel();
		panelDireita.setBackground(new Color(164, 247, 176));
		getContentPane().add(panelDireita, "cell 1 0,grow");
		panelDireita.setLayout(new MigLayout("fill", "[][][][grow][grow][][][grow][][]", "[][][][grow][][grow][grow][][grow][][][grow]"));

		JLabel lblTituloPanel2 = new JLabel("Seja bem-vindo!");
		lblTituloPanel2.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelDireita.add(lblTituloPanel2, "cell 4 2 2 1,alignx center");

		JLabel lblAcesseComoAdm = new JLabel("Acesse como administrador:");
		lblAcesseComoAdm.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelDireita.add(lblAcesseComoAdm, "cell 4 4,alignx center,aligny center");

		JPanel panelBtnAdm = new JPanel();
		panelBtnAdm.setBackground(new Color(192, 192, 192));
		panelDireita.add(panelBtnAdm, "cell 4 5 2 1, grow, alignx center");
		panelBtnAdm.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		JButton btnAcessoAdm = new JButton(resizedAdm);
		panelBtnAdm.add(btnAcessoAdm);
		btnAcessoAdm.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ADMLogin admLogin = new ADMLogin();
				admLogin.setVisible(true);
				dispose();
			}
		});

		JLabel lblAcesseComoVotante = new JLabel("Acesse como votante:");
		lblAcesseComoVotante.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelDireita.add(lblAcesseComoVotante, "cell 4 7,alignx center,aligny center");

		JPanel panelBtnVotante = new JPanel();
		panelBtnVotante.setBackground(new Color(192, 192, 192));
		panelDireita.add(panelBtnVotante, "cell 4 8 2 1, grow, alignx center");
		panelBtnVotante.setLayout(new GridLayout(1, 0, 0, 0));
		

		JButton btnAcessoVotante = new JButton(resizedUser);
		panelBtnVotante.add(btnAcessoVotante);
		btnAcessoVotante.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginUsuario login = new LoginUsuario();
				login.setVisible(true);
				dispose();
			}
		});
	}

}
