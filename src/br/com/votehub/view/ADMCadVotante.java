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

//import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;	

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerVotante;
import net.miginfocom.swing.MigLayout;

public class ADMCadVotante extends JFrame {

	private JPanel contentPane;
	private JTextField fieldMatricula;
	private JTextField fieldNome;
	private JPasswordField fieldSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ADMCadVotante frame = new ADMCadVotante();
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
	public ADMCadVotante() {
		setTitle("Cadastro de Votante");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 381);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("fill", "[grow][][][][][][][][][][grow][][grow]", "[][][][][][][][]"));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 5 0 1 3,alignx center,aligny center");
		panel.setPreferredSize(new Dimension(800, 600)); 
		panel.setLayout(new MigLayout("fill", "[grow][][grow][grow][][][grow][][grow]", "[][][][][][][][][][][]"));
		
		JLabel lblCadVotante = new JLabel("Cadastro de Votante");
		lblCadVotante.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblCadVotante, "cell 0 0 9 1,alignx center,aligny center");
		
		JButton btnVoltarCad = new JButton("VOLTAR");
		btnVoltarCad.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVoltarCad.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                ADMCadastro admCadastro = new ADMCadastro();
	                admCadastro.setVisible(true);
	                dispose();
	            }
	        });
		
		JLabel lblCadNome = new JLabel("Nome :");
		lblCadNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCadNome, "cell 1 3,alignx trailing");
		
		fieldNome = new JTextField();
		panel.add(fieldNome, "cell 2 3 6 1,growx");
		fieldNome.setColumns(10);
		
		JLabel lblCadMatricula = new JLabel("Matrícula :");
		lblCadMatricula.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCadMatricula, "cell 1 4,alignx trailing,aligny baseline");
		
		fieldMatricula = new JTextField();
		panel.add(fieldMatricula, "cell 2 4 6 1,growx");
		fieldMatricula.setColumns(10);
		
		JLabel lblCadSenha = new JLabel("Senha :");
		lblCadSenha.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCadSenha, "cell 1 5,alignx trailing");
		
		fieldSenha = new JPasswordField();
		panel.add(fieldSenha, "cell 2 5 6 1,growx");
		fieldSenha.setColumns(10);
		panel.add(btnVoltarCad, "cell 0 10,alignx center,aligny bottom");
		
		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnCadastrar, "cell 8 10,alignx right,aligny bottom");
		btnCadastrar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String matricula = fieldMatricula.getText();
		        String nome = fieldNome.getText();
		        String senha = fieldSenha.getText();

		        ControllerVotante contVotante = new ControllerVotante();
		        try {
		            contVotante.registrarVotante(matricula, nome, senha);
		            JOptionPane.showMessageDialog(null, "Votante cadastrado com sucesso!");
		            
		            fieldNome.setText("");
					fieldMatricula.setText("");
					fieldSenha.setText("");
		        } catch (BusinessException error) {
		            JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		        } catch (SQLException error2) {
		            JOptionPane.showMessageDialog(null, error2.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
	}
}

