package br.com.votehub.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.votehub.controller.ControllerVotante;
import br.com.votehub.model.DAOs.DbIntegrityException;
import br.com.votehub.model.vo.Proposta;
import br.com.votehub.model.vo.Votante;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerProposta;

public class ADMConsultaProposta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldTitulo;
	private JTextField textFieldId;
	private JTextField textFieldDescricao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ADMConsultaProposta frame = new ADMConsultaProposta();
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
	public ADMConsultaProposta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 558, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][grow][grow][][][][][][][]", "[][][][][][][][][][][][]"));

		JLabel lblProposta = new JLabel("Propostas");
		lblProposta.setHorizontalAlignment(SwingConstants.CENTER);
		lblProposta.setFont(new Font("Tahoma", Font.BOLD, 17));
		contentPane.add(lblProposta, "cell 5 0 3 1");

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblId, "cell 1 2");

		textFieldId = new JTextField();
		contentPane.add(textFieldId, "cell 2 2 8 1,growx");
		textFieldId.setColumns(10);

		JLabel lblTitulo = new JLabel("Título");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblTitulo, "cell 1 4");

		textFieldTitulo = new JTextField();
		contentPane.add(textFieldTitulo, "cell 2 4 8 1,growx");
		textFieldTitulo.setColumns(10);

		JLabel lblDescricao = new JLabel("Descrição");
		lblDescricao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblDescricao, "cell 1 6");

		textFieldDescricao = new JTextField();
		contentPane.add(textFieldDescricao, "cell 2 6 8 1,growx");
		textFieldDescricao.setColumns(10);

		JButton btnConsultar = new JButton("Consultar");
		contentPane.add(btnConsultar, "cell 2 9 2 1");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					int idProposta = Integer.parseInt(textFieldId.getText());
					ControllerProposta controllerProposta = new ControllerProposta();
					Proposta proposta = controllerProposta.buscarProposta(idProposta);

					if (proposta == null) {
						JOptionPane.showMessageDialog(null, "Proposta não encontrada.");
					} else {
						textFieldTitulo.setText(proposta.getTitulo());
						textFieldDescricao.setText(proposta.getDescricao());
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Insira um ID válido.");
				}
			}
		});

		JButton btnEditar = new JButton("Editar");
		btnEditar.setHorizontalAlignment(SwingConstants.LEADING);
		contentPane.add(btnEditar, "cell 6 9");
		btnEditar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {

		            if (textFieldId.getText().isEmpty() || textFieldTitulo.getText().isEmpty()
		                    || textFieldDescricao.getText().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Preencha todos os campos antes de editar.");
		                return;
		            }

		            int id = Integer.parseInt(textFieldId.getText());
		            String novoTitulo = textFieldTitulo.getText();
		            String novaDescricao = textFieldDescricao.getText();

		            ControllerProposta controller = new ControllerProposta();
		            controller.atualizarProposta(id, novoTitulo, novaDescricao);

		            JOptionPane.showMessageDialog(null, "Proposta atualizada com sucesso.");
		        } catch (BusinessException ex) {
		            JOptionPane.showMessageDialog(null, ex.getMessage());
		        }
		    }
		});


		JButton btnDeletar = new JButton("Deletar");
		contentPane.add(btnDeletar, "cell 9 9");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					if (textFieldId.getText().isEmpty() || textFieldTitulo.getText().isEmpty()
							|| textFieldDescricao.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Preencha todos os campos antes de deletar.");
						return;
					}

					int id = Integer.parseInt(textFieldId.getText());

					ControllerProposta controller = new ControllerProposta();
					controller.deletarProposta(id);

					textFieldId.setText("");
					textFieldTitulo.setText("");
					textFieldDescricao.setText("");

					JOptionPane.showMessageDialog(null, "Proposta deletada com sucesso.");
				} catch (BusinessException error) {
					JOptionPane.showMessageDialog(null, error.getMessage());
				} catch (DbIntegrityException error2) {
					JOptionPane.showMessageDialog(null, error2.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton btnVoltar = new JButton("Voltar");
		contentPane.add(btnVoltar, "cell 0 11");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaConsulta tela = new TelaConsulta();
				tela.setVisible(true);
				dispose();
			}
		});
	}

}
