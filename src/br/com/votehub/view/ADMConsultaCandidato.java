package br.com.votehub.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class ADMConsultaCandidato extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNumeroCandidato;
	private JTextField textFieldNome;
	private JTextField textFieldCargo;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ADMConsultaCandidato frame = new ADMConsultaCandidato();
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
	public ADMConsultaCandidato() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 682, 611);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][grow][grow][][][][][][][][][][][grow][][]", "[][][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblTitulo = new JLabel("Candidatos");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 17));
		contentPane.add(lblTitulo, "cell 6 0");
		
		JLabel lblNumeroCandidato = new JLabel("N° Candidato");
		lblNumeroCandidato.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblNumeroCandidato, "cell 1 2");
		
		textFieldNumeroCandidato = new JTextField();
		contentPane.add(textFieldNumeroCandidato, "cell 3 2 12 1,growx");
		textFieldNumeroCandidato.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblNome, "cell 1 4");
		
		textFieldNome = new JTextField();
		contentPane.add(textFieldNome, "cell 3 4 12 1,growx");
		textFieldNome.setColumns(10);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblCargo, "cell 1 6");
		
		textFieldCargo = new JTextField();
		contentPane.add(textFieldCargo, "cell 3 6 12 1,growx");
		textFieldCargo.setColumns(10);
		
		JLabel Eleição = new JLabel("ID Eleição");
		Eleição.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(Eleição, "cell 1 8");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 3 8 12 1,growx");
		textField.setColumns(10);
		
		JButton btnConsultar = new JButton("Consultar");
		contentPane.add(btnConsultar, "cell 6 13");
		
		JButton btnDeletar = new JButton("Deletar");
		contentPane.add(btnDeletar, "cell 7 13");
		
		JButton btnEditar = new JButton("Editar");
		contentPane.add(btnEditar, "cell 9 13");
		
		JButton btnVoltar = new JButton("Voltar");
		contentPane.add(btnVoltar, "cell 0 19");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaConsulta consulta = new TelaConsulta();
				consulta.setVisible(true);
				dispose();
			}
		});
	}

}
