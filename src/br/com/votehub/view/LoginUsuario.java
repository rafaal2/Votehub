package br.com.votehub.view;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.JFormattedTextField;

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
		try {
			MaskFormatter maskara = new MaskFormatter("**************");
			campoMatricula = new JFormattedTextField(maskara);
			campoMatricula.setToolTipText("Utilize Apenas Números e Letras.  ex: 20001r31rc0234");
			campoMatricula = new JFormattedTextField(maskara);   
            campoMatricula.setColumns(11);
        } catch (ParseException e) {
            e.printStackTrace();
        }
		getContentPane().add(campoMatricula, "cell 2 3,growx");
		
		lblNewLabel_1 = new JLabel("CPF:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblNewLabel_1, "cell 1 4,alignx trailing");
		
		// AÇÃO BOTÃO ENTRAR
		JButton botaoEntrar = new JButton("Entrar");
		botaoEntrar.addActionListener(new ActionListener() {
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
		getContentPane().add(botaoEntrar, "cell 2 5,alignx center");
		
		
		// Formatacao Campo CPF	
		try {
			MaskFormatter maskara = new MaskFormatter("###########");
			campoCpf = new JFormattedTextField(maskara);   
			campoCpf.setToolTipText("Utilize Apenas Números.    ex:12378912301");
            campoCpf.setColumns(11);
            campoCpf.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!Character.isDigit(c)) {
                        e.consume(); 
                    }
                }

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
            });

        } catch (ParseException e) {
            e.printStackTrace();
        }
		getContentPane().add(campoCpf, "cell 2 4,growx");
		

		
	}

}
