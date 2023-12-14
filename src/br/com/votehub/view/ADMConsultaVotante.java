package br.com.votehub.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.votehub.model.vo.Proposta;
import br.com.votehub.model.vo.Votante;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerProposta;
import br.com.votehub.controller.ControllerVotante;
import br.com.votehub.model.DAOs.DbIntegrityException;
import br.com.votehub.model.DAOs.VotanteDAO;

public class ADMConsultaVotante extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldId;
	private JTextField textFieldNome;
	private JTextField textFieldMatricula;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ADMConsultaVotante frame = new ADMConsultaVotante();
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

	public ADMConsultaVotante() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 558, 398);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("fill", "[grow][][][][][][][][][][grow][][grow]", "[][][][][][][][]"));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(164, 247, 176));
		contentPane.add(panel, "cell 6 0 1 3,alignx center,aligny center");
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(new MigLayout("fill", "[grow][][][][grow][][grow]",
				"[][][][][][][][][][][][][][][][][][][][][][][][][]"));

		JLabel lblVotantes = new JLabel("Votantes");
		lblVotantes.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblVotantes, "cell 4 1,alignx center");

		JLabel lblMatricula = new JLabel("Matrícula");
		lblMatricula.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblMatricula, "cell 3 7,alignx center");

		textFieldMatricula = new JTextField();
		panel.add(textFieldMatricula, "cell 4 7,growx");
		textFieldMatricula.setColumns(10);

		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblId, "cell 3 9,alignx center");
		

		textFieldId = new JTextField();
		panel.add(textFieldId, "cell 4 9,growx");
		textFieldId.setColumns(10);
		textFieldId.setEditable(false);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblNome, "cell 3 11,alignx center");

		textFieldNome = new JTextField();
		panel.add(textFieldNome, "cell 4 11,growx");
		textFieldNome.setColumns(10);

		JButton btnEditar = new JButton("Editar");
		panel.add(btnEditar, "cell 3 15,alignx center");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					if (textFieldId.getText().isEmpty() || textFieldMatricula.getText().isEmpty()
							|| textFieldNome.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Preencha todos os campos antes de editar.");
						return;
					}

					int idVotante = Integer.parseInt(textFieldId.getText());
					String novaMatricula = textFieldMatricula.getText();
					String novoNome = textFieldNome.getText();

					ControllerVotante controller = new ControllerVotante();
					controller.atualizarVotante(idVotante, novaMatricula, novoNome);

					JOptionPane.showMessageDialog(null, "Votante atualizado com sucesso.");
				} catch (BusinessException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				} catch (SQLException e1) {
					JOptionPane.showConfirmDialog(null, e1.getMessage());
				}
			}
		});

		JButton btnConsultar = new JButton("Consultar");
		panel.add(btnConsultar, "cell 4 15,alignx center");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String matricula = textFieldMatricula.getText();
				ControllerVotante controllerVotante = new ControllerVotante();
				Votante votante = controllerVotante.buscarVotante(matricula);

				if (votante == null) {
					JOptionPane.showMessageDialog(null, "Votante não encontrado.");
				} else {
					textFieldId.setText(Integer.toString(votante.getId_votante()));
					textFieldNome.setText(votante.getNome());
//		            textFieldSenha.setText(votante.getSenha());
				}
			}
		});

		JButton btnDeletar = new JButton("Deletar");
		panel.add(btnDeletar, "cell 5 15,alignx center");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					if (textFieldId.getText().isEmpty() || textFieldMatricula.getText().isEmpty()
							|| textFieldNome.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Preencha todos os campos antes de deletar.");
						return;
					}

					int idVotante = Integer.parseInt(textFieldId.getText());

					ControllerVotante controller = new ControllerVotante();
					controller.deletarVotante(idVotante);

					textFieldMatricula.setText("");
					textFieldNome.setText("");
					textFieldId.setText("");
//		            textFieldSenha.setText("");

					JOptionPane.showMessageDialog(null, "Votante deletado com sucesso.");
				} catch (BusinessException error) {
					JOptionPane.showMessageDialog(null, "O votante não pode ser deletado pois já votou.");
				} catch (DbIntegrityException error2) {
					JOptionPane.showMessageDialog(null, error2.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton btnVoltar = new JButton("VOLTAR");
		panel.add(btnVoltar, "cell 4 23,alignx center");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ADMMenuConsulta consulta = new ADMMenuConsulta();
				consulta.setVisible(true);
				dispose();
			}
		});
	}
}
