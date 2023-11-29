package br.com.votehub.view;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.text.MaskFormatter;

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerVotante;
import net.miginfocom.swing.MigLayout;

public class LoginUsuario extends JFrame {

	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	String ano = Integer.toString(LocalDate.now().getYear());
	private JFormattedTextField campoMatricula;
	private JFormattedTextField campoCpf;
	
	// CHAMANDO A TELA -> SwingUtilities.invokeLater(LoginUsuario::new);
	// PARA VOLTAR A TELA INICIAL -> CTRL + I
	// NAO E POSSIVEL FECHAR A JANELA, PARA FECHAR VOLTE PARA TELA INICIAL
	
	public LoginUsuario() {
		try {
			initialize();
			setExtendedState(JFrame.MAXIMIZED_BOTH);
	        setVisible(true);
	        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_I, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
            getRootPane().registerKeyboardAction(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	TelaInicial telaInicial = new TelaInicial();
            		telaInicial.setVisible(true);
            		dispose();
            		System.out.println("Volta a Tela Inicial");
                }
            }, keyStroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
	        
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao inicializar a Tela de Login de Usuário: " + e.getMessage());
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Login Usuário");
		//frame.setSize(1366, 768);//eixo x e eixo y
		//frame.resizable(false);
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("fill", "[grow][][grow][][grow]", "[][][][][][][][]"));
		
		lblNewLabel_2 = new JLabel("Eleição " + ano);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		getContentPane().add(lblNewLabel_2, "cell 2 2,alignx center");
		
		lblNewLabel = new JLabel("Matrícula:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		getContentPane().add(lblNewLabel, "cell 1 3,alignx trailing");
		
		// Formatacao Campo Matricula
			campoMatricula = new JFormattedTextField();
			campoMatricula.setToolTipText("Utilize Apenas Números e Letras.  ex: 20001r31rc0234"); 
		getContentPane().add(campoMatricula, "cell 2 3,growx");
		
		lblNewLabel_1 = new JLabel("CPF:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblNewLabel_1, "cell 1 4,alignx trailing");
		
		// AÇÃO BOTÃO ENTRAR
		JButton botaoEntrar = new JButton("Entrar");
		botaoEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent entrar) {
					String loginDigitada = campoMatricula.getText();
					String senhaDigitada = campoCpf.getText();
					try {
						ControllerVotante contvot = new ControllerVotante();
						contvot.verificarloginvot(loginDigitada);
						contvot.verificarsenhavot(senhaDigitada);
						TelaVotacao loginVoto = new TelaVotacao();
						loginVoto.setVisible(true);
                		dispose();
					} catch (BusinessException error) {
						JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					} catch (SQLException e1) {
						e1.printStackTrace();
					} 

			}
		});
		getContentPane().add(botaoEntrar, "cell 2 5,alignx center");
		
		
		// Formatacao Campo CPF	
		
			campoCpf = new JFormattedTextField();   
			campoCpf.setToolTipText("Utilize Apenas Números.    ex:12378912301");
         
		getContentPane().add(campoCpf, "cell 2 4,growx");
		

		
	}

}
