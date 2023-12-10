package br.com.votehub.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;

public class TelaLoginUsuarioModelo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldMatricula;
	private JTextField textFieldSenha;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLoginUsuarioModelo frame = new TelaLoginUsuarioModelo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaLoginUsuarioModelo() {
		try {
			initialize();
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			setVisible(true);
			KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_I,
					Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
			getRootPane().registerKeyboardAction(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					TelaInicial telaInicial = new TelaInicial();
					telaInicial.setVisible(true);
					dispose();
				}
			}, keyStroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao inicializar a Tela de Login de Usuário: " + e.getMessage());
		}
	}

	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow]"));

		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(new Color(164, 247, 176));
		contentPane.add(panelInferior, "cell 0 0, grow");

		panelInferior.setLayout(new MigLayout("", "[][grow][]", "[][][][][][][][][]"));

		JLabel lblTitulo = new JLabel("Área do votante");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		panelInferior.add(lblTitulo, "cell 1 0, growx, aligny center");

		JLabel lblMatricula = new JLabel("Matrícula:");
		lblMatricula.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelInferior.add(lblMatricula, "cell 0 2,alignx trailing");

		textFieldMatricula = new JTextField();
		panelInferior.add(textFieldMatricula, "cell 1 2,growx,aligny center");

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelInferior.add(lblSenha, "cell 0 4,alignx trailing");

		textFieldSenha = new JTextField();
		panelInferior.add(textFieldSenha, "cell 1 4,growx,aligny center");

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelInferior.add(btnEntrar, "cell 1 6,alignx center");
		
		JButton btnVoltar = new JButton("Voltar");
		panelInferior.add(btnVoltar, "cell 0 8");

		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(new Color(192, 192, 192));
		contentPane.add(panelSuperior, "cell 0 0, grow");

		panelSuperior.setLayout(new MigLayout("", "[grow][][grow]", "[grow]"));

		ImageIcon user = new ImageIcon("./icons/user.png");
		Image userImg = user.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		ImageIcon resizedUser = new ImageIcon(userImg);
		JLabel lblIcone = new JLabel(resizedUser);
		panelSuperior.add(lblIcone, "cell 1 0, align center, aligny center");
	}
}
