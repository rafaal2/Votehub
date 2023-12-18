package br.com.votehub.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import br.com.votehub.controller.ControllerVotante;
import br.com.votehub.model.DAOs.DbIntegrityException;
import br.com.votehub.model.vo.Proposta;
import br.com.votehub.model.vo.Votante;
import net.miginfocom.swing.MigLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerProposta;

public class ADMConsultaProposta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldId;
	private JTextField textFieldTitulo;
	private JTextField textFieldDescricao;
	private JList<String> list;
    private DefaultListModel<String> listModel;


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
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 558, 398);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(164, 247, 176));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("fill", "[grow][][][][][][][][][][grow][][grow]", "[][][][][][][][]"));

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(SystemColor.menu);
		contentPane.add(panel, "cell 1 0 1 3,alignx center,aligny center");
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(
				new MigLayout("fill", "[grow][][][][grow][][grow]", "[][][][][][][][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblProposta = new JLabel("Propostas");
		lblProposta.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblProposta, "cell 4 1,alignx center");
		
		JLabel lblId = new JLabel("ID: ");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblId, "cell 3 7,alignx center");
		
		textFieldId = new JTextField();
		panel.add(textFieldId, "cell 4 7,growx");
		textFieldId.setColumns(10);
		
		JLabel lblTitulo = new JLabel("Título: ");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblTitulo, "cell 3 9,alignx center");
		
		listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setBackground(SystemColor.menu);
        list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        contentPane.add(list, "cell 4 0 1 3,alignx center,aligny center");
        atualizarListaProposta();
		
		textFieldTitulo = new JTextField();
		panel.add(textFieldTitulo, "cell 4 9,growx");
		textFieldTitulo.setColumns(10);
		
		JLabel lblDescricao = new JLabel("Descrição: ");
		lblDescricao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblDescricao, "cell 3 11,alignx center");
		
		textFieldDescricao = new JTextField();
		panel.add(textFieldDescricao, "cell 4 11,growx");
		textFieldDescricao.setColumns(10);
		
		JButton btnEditar = new JButton("Editar");
		panel.add(btnEditar, "cell 3 15,alignx center");
		btnEditar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {

		            if (textFieldId.getText().isEmpty() || textFieldTitulo.getText().isEmpty() || textFieldDescricao.getText().isEmpty()) {
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
		
		JButton btnConsultar = new JButton("Consultar");
		panel.add(btnConsultar, "cell 4 15,alignx center");
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
		
		JButton btnDeletar = new JButton("Deletar");
		panel.add(btnDeletar, "cell 5 15,alignx center");
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
		
		JButton btnVoltar = new JButton("VOLTAR");
		panel.add(btnVoltar, "cell 4 23,alignx center");
		
		JLabel lblNewLabel = new JLabel("Propostas Cadastradas:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblNewLabel, "cell 3 0 1 3");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ADMMenuConsulta tela = new ADMMenuConsulta();
				tela.setVisible(true);
				dispose();
			}
		});
	}
	
	 private void atualizarListaProposta() {
		  
	        listModel.clear();

	        ControllerProposta controllerProposta = new ControllerProposta();
	        List<Proposta> propostas = controllerProposta.ExibirPropostas();
	        for (Proposta proposta : propostas) {
	            listModel.addElement("ID : " + proposta.getId_Proposta() + " | Titulo : " + proposta.getTitulo());
	        }
	    }

}
