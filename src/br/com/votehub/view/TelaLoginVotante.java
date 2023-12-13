package br.com.votehub.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerVotacaoVotante;
import br.com.votehub.controller.ControllerVotante;
import br.com.votehub.model.DAOs.DbException;
import br.com.votehub.model.vo.Votante;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;

public class TelaLoginVotante extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldMatricula;
	private JPasswordField textFieldSenha;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLoginVotante frame = new TelaLoginVotante();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaLoginVotante() {
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
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("fill", "[grow]", "[grow]"));

		ImageIcon user = new ImageIcon("./icons/user.png");
		Image userImg = user.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
		ImageIcon resizedUser = new ImageIcon(userImg);
		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(new Color(164, 247, 176));
		contentPane.add(panelInferior, "cell 0 0, grow");

		panelInferior.setLayout(new MigLayout("fill", "[][grow][][grow][][grow][]", "[][][][][][][][][][]"));

		JLabel lblTitulo = new JLabel("Área do Votante");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		panelInferior.add(lblTitulo, "cell 3 0,growx,aligny bottom");

		JLabel lblMatricula = new JLabel("Matrícula:");
		lblMatricula.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelInferior.add(lblMatricula, "cell 2 2,alignx trailing");

		textFieldMatricula = new JTextField();
		panelInferior.add(textFieldMatricula, "cell 3 2,growx,aligny center");
		textFieldMatricula.setToolTipText("Utilize Apenas Números e Letras.  ex: 20001r31rc0234");

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelInferior.add(lblSenha, "cell 2 4,alignx trailing");

		textFieldSenha = new JPasswordField();
		panelInferior.add(textFieldSenha, "cell 3 4,growx,aligny center");
		textFieldSenha.setToolTipText("Utilize Apenas Números.    ex:12378912301");

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String loginDigitada = textFieldMatricula.getText();
				String senhaDigitada = textFieldSenha.getText();
				try {
					ControllerVotacaoVotante contVotacaoVotante = new ControllerVotacaoVotante();
					ControllerVotante contvot = new ControllerVotante();
					Votante vtt = contvot.buscarVotante(loginDigitada);
					
					//Proibe Votantes que ja votaram de acessar a tela de votação
					//contVotacaoVotante.checarVotabilidade(vtt.getId_votante());
												
					contvot.verificarloginvot(loginDigitada);
					contvot.verificarsenhavot(loginDigitada, senhaDigitada);
					TelaSelectVotacao selectVotacao = new TelaSelectVotacao(vtt);
					selectVotacao.setVisible(true);
            		dispose();
				} catch (BusinessException error) {
					JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEntrar.setPreferredSize(new Dimension(40, 30));
		panelInferior.add(btnEntrar, "cell 3 6,alignx center");

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVoltar.setPreferredSize(new Dimension(5, 5));
		// panelInferior.add(btnVoltar, "cell 0 8,alignx center");

		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(new Color(192, 192, 192));
		contentPane.add(panelSuperior, "cell 0 0, grow");
		panelSuperior.setLayout(new MigLayout("fill", "[grow][][grow]", "[grow][][grow]"));

		JLabel lblNewLabel = new JLabel(resizedUser);
		panelSuperior.add(lblNewLabel, "cell 1 1");

	}
}
