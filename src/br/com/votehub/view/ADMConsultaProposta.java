package br.com.votehub.view;

import java.awt.Color;
import java.awt.Dimension;
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
	private JTextField textFieldId;
	private JTextField textFieldTitulo;
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
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("fill", "[grow][][][][][][][][][][grow][][grow]", "[][][][][][][][]"));

		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 6 0 1 3,alignx center,aligny center");
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
		
		JButton btnConsultar = new JButton("Consultar");
		panel.add(btnConsultar, "cell 4 15,alignx center");
		
		JButton btnDeletar = new JButton("Deletar");
		panel.add(btnDeletar, "cell 5 15,alignx center");
		
		JButton btnVoltar = new JButton("VOLTAR");
		panel.add(btnVoltar, "cell 4 23,alignx center");
	}

}
