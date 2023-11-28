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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

//import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;	

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerVotante;
import net.miginfocom.swing.MigLayout;

public class ADMCadEleitor extends JFrame {

	private JPanel contentPane;
	private JTextField fieldMatriculaCad;
	private JTextField fieldNomeCad;
	private JTextField filedSenhaCad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ADMCadEleitor frame = new ADMCadEleitor();
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
	public ADMCadEleitor() {
		setTitle("Cadastro de Eleitor");
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
		
		JLabel lblCadEleitor = new JLabel("Cadastro de Eleitor");
		lblCadEleitor.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblCadEleitor, "cell 0 0 9 1,alignx center,aligny center");
		
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
		
		fieldNomeCad = new JTextField();
		panel.add(fieldNomeCad, "cell 2 3 6 1,growx");
		fieldNomeCad.setColumns(10);
		
		JLabel lblCadMatricula = new JLabel("Matrícula :");
		lblCadMatricula.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCadMatricula, "cell 1 4,alignx trailing,aligny baseline");
		
		fieldMatriculaCad = new JTextField();
		panel.add(fieldMatriculaCad, "cell 2 4 6 1,growx");
		fieldMatriculaCad.setColumns(10);
		
		JLabel lblCadSenha = new JLabel("Senha :");
		lblCadSenha.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCadSenha, "cell 1 5,alignx trailing");
		
		filedSenhaCad = new JTextField();
		panel.add(filedSenhaCad, "cell 2 5 6 1,growx");
		filedSenhaCad.setColumns(10);
		panel.add(btnVoltarCad, "cell 0 10,alignx center,aligny bottom");
		
		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnCadastrar, "cell 8 10,alignx right,aligny bottom");
		btnCadastrar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String matricula = fieldMatriculaCad.getText();
		        String nome = fieldNomeCad.getText();
		        String senha = filedSenhaCad.getText();

		        ControllerVotante contVotante = new ControllerVotante();
		        try {
		            contVotante.registrarVotante(matricula, nome, senha);
		            JOptionPane.showMessageDialog(null, "Votante cadastrado com sucesso!");
		        } catch (BusinessException error) {
		            JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		        } catch (SQLException error2) {
		            JOptionPane.showMessageDialog(null, error2.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
	}
}

