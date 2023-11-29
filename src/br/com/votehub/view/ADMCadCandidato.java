package br.com.votehub.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

//import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;	

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerCandidato;
import net.miginfocom.swing.MigLayout;

public class ADMCadCandidato extends JFrame {

	private JPanel contentPane;
	private JTextField fieldNomeCad;
	private JTextField fieldNumCad;
	private JTextField filedCargoCad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ADMCadCandidato frame = new ADMCadCandidato();
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
	public ADMCadCandidato() {
		setTitle("Cadastro de Candidato");
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
		
		JLabel lblCadCandidato = new JLabel("Cadastro de Candidato");
		lblCadCandidato.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblCadCandidato, "cell 0 0 9 1,alignx center,aligny center");
		
		JButton btnVoltarCad = new JButton("VOLTAR");
		btnVoltarCad.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVoltarCad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblCadNome = new JLabel("Nome :");
		lblCadNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCadNome, "cell 1 3,alignx trailing");
		
		fieldNomeCad = new JTextField();
		panel.add(fieldNomeCad, "cell 2 3 6 1,growx");
		fieldNomeCad.setColumns(10);
		
		JLabel lblCadNumCand = new JLabel("Nº Candidato :");
		lblCadNumCand.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCadNumCand, "cell 1 4,alignx trailing");
		
		fieldNumCad = new JTextField();
		panel.add(fieldNumCad, "cell 2 4 6 1,growx");
		fieldNumCad.setColumns(10);
		
		JLabel lblCadCargo = new JLabel("Cargo :");
		lblCadCargo.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCadCargo, "cell 1 5,alignx trailing");
		
		filedCargoCad = new JTextField();
		panel.add(filedCargoCad, "cell 2 5 6 1,growx");
		filedCargoCad.setColumns(10);
		panel.add(btnVoltarCad, "cell 0 10,alignx center,aligny bottom");
		
		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnCadastrar, "cell 8 10,alignx right,aligny bottom");
		
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numero_candidato = fieldNumCad.getText();
				String nomeCandidato = fieldNomeCad.getText();
				String cargoCandidato = filedCargoCad.getText();
				int id_votacao = 1;
				
				try {
					ControllerCandidato contCandidato = new ControllerCandidato();
					contCandidato.registrarCandidato(numero_candidato, nomeCandidato, cargoCandidato, id_votacao);
				} catch(BusinessException error) {
					JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		});
	}

}
