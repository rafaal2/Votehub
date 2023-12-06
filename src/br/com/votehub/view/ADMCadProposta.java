package br.com.votehub.view;

import java.awt.Color;
import java.awt.Dimension;
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

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerProposta;
import net.miginfocom.swing.MigLayout;

public class ADMCadProposta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fieldTituloCad;
	private JTextField fieldDescricaoCad;
	private JTextField filedIdEleicao;
	//private JComboBox<String> comboBoxRespostaCad;

	public ADMCadProposta() {
		setTitle("Cadastro de Proposta");
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

		JLabel lblCadCandidato = new JLabel("Cadastro de Proposta");
		lblCadCandidato.setBounds(303, 26, 194, 21);
		lblCadCandidato.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblCadCandidato, "cell 0 0 9 1,alignx center,aligny center");

		JButton btnVoltarCad = new JButton("VOLTAR");
		btnVoltarCad.setBounds(44, 570, 81, 23);
		btnVoltarCad.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVoltarCad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ADMCadastro admCadastro = new ADMCadastro();
				admCadastro.setVisible(true);
				dispose();
			}
		});

		JLabel lblCadTitulo = new JLabel("título :");
		lblCadTitulo.setBounds(206, 150, 38, 14);
		lblCadTitulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCadTitulo, "cell 1 3,alignx trailing");

		fieldTituloCad = new JTextField();
		fieldTituloCad.setBounds(248, 150, 359, 20);
		panel.add(fieldTituloCad, "cell 2 3 6 1,growx");
		fieldTituloCad.setColumns(10);

		JLabel lblCadDescricao = new JLabel("Descrição :");
		lblCadDescricao.setBounds(165, 200, 79, 14);
		lblCadDescricao.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCadDescricao, "cell 1 4,alignx trailing,aligny baseline");
		
		//comboBoxRespostaCad = new JComboBox<>(new String[]{"Sim", "Provavelmente sim", "Talvez", "Provavelmente não", "não"});
		//comboBoxRespostaCad.setBounds(248, 200, 359, 20);
		//panel.add(comboBoxRespostaCad, "cell 2 4 6 1,growx");

		fieldDescricaoCad = new JTextField();
		fieldDescricaoCad.setBounds(248, 200, 359, 20);
		panel.add(fieldDescricaoCad, "cell 2 4 6 1,growx");
		fieldDescricaoCad.setColumns(10);

		JLabel lblCadIdEleicao = new JLabel("Nº da Eleição :");
		lblCadIdEleicao.setBounds(166, 250, 78, 14);
		lblCadIdEleicao.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCadIdEleicao, "cell 1 5,alignx trailing");

		filedIdEleicao = new JTextField();
		filedIdEleicao.setBounds(248, 250, 359, 20);
		panel.add(filedIdEleicao, "cell 2 5 6 1,growx");
		filedIdEleicao.setColumns(10);
		
		
		JButton btnCadVoltar = new JButton("VOLTAR");
		btnCadVoltar.setBounds(684, 570, 109, 23);
		btnCadVoltar.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnCadVoltar, "cell 3 11,alignx center,aligny center");
		btnCadVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ADMCadastro admcadastro = new ADMCadastro();
				admcadastro.setVisible(true);
				dispose();
			}
		});

		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setBounds(684, 570, 109, 23);
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnCadastrar, "cell 8 10,alignx right,aligny bottom");
		btnCadastrar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String titulo = fieldTituloCad.getText();
		        String descricao = fieldDescricaoCad.getText();
		        int id_votacao = Integer.parseInt(filedIdEleicao.getText());

		        ControllerProposta contVotante = new  ControllerProposta();
		        try {
		            contVotante.registrarProposta(titulo, descricao, id_votacao);
		            JOptionPane.showMessageDialog(null, "Proposta cadastrada com sucesso!");
		        } catch (BusinessException error) {
		            JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		        } catch (SQLException error2) {
		            JOptionPane.showMessageDialog(null, error2.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
	}

}
