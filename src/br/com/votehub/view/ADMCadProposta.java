package br.com.votehub.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerProposta;
import br.com.votehub.controller.ControllerVotacao;
import net.miginfocom.swing.MigLayout;

public class ADMCadProposta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fieldTituloCad;
	private JTextField fieldDescricaoCad;
	private JTextField filedIdVotacao;
	private JComboBox<Object> comboBoxNumeroVotacao;
	// private JComboBox<String> comboBoxRespostaCad;

	public ADMCadProposta() {
		setTitle("Cadastro de Proposta");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 381);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(164, 247, 176));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("fill", "[grow][][][][][][][][][][grow][][grow]", "[][][][][][][][]"));
		
		

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		contentPane.add(panel, "cell 5 0 1 3,alignx center,aligny center");
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(
				new MigLayout("fill", "[grow][][grow][grow][][][][grow][][grow]", "[][][][][][][][][][][][][][]"));

		JLabel lblCadCandidato = new JLabel("Cadastro de Proposta");
		lblCadCandidato.setBounds(303, 26, 194, 21);
		lblCadCandidato.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblCadCandidato, "cell 0 0 10 1,alignx center,aligny center");

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

		JLabel lblCadTitulo = new JLabel("Título:");
		lblCadTitulo.setBounds(206, 150, 38, 14);
		lblCadTitulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCadTitulo, "cell 1 3,alignx trailing");

		fieldTituloCad = new JTextField();
		fieldTituloCad.setBounds(248, 150, 359, 20);
		panel.add(fieldTituloCad, "cell 2 3 7 1,growx");
		fieldTituloCad.setColumns(10);

		JLabel lblCadDescricao = new JLabel("Descrição:");
		lblCadDescricao.setBounds(165, 200, 79, 14);
		lblCadDescricao.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCadDescricao, "cell 1 4,alignx trailing,aligny baseline");

		// comboBoxRespostaCad = new JComboBox<>(new String[]{"Sim", "Provavelmente
		// sim", "Talvez", "Provavelmente não", "não"});
		// comboBoxRespostaCad.setBounds(248, 200, 359, 20);
		// panel.add(comboBoxRespostaCad, "cell 2 4 6 1,growx");

		fieldDescricaoCad = new JTextField();
		fieldDescricaoCad.setBounds(248, 200, 359, 20);
		panel.add(fieldDescricaoCad, "cell 2 4 7 1,growx");
		fieldDescricaoCad.setColumns(10);

		JLabel lblCadIdVotacao = new JLabel("Nº da Votação:");
		lblCadIdVotacao.setBounds(166, 250, 78, 14);
		lblCadIdVotacao.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCadIdVotacao, "cell 1 5,alignx trailing");

		comboBoxNumeroVotacao = new JComboBox<>();
		comboBoxNumeroVotacao.setBounds(248, 250, 359, 20);
		panel.add(comboBoxNumeroVotacao);
		restaurarIdEleicaoCombobox();
		
		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setBounds(684, 570, 109, 23);
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnCadastrar, "cell 5 8,alignx right,aligny bottom");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titulo = fieldTituloCad.getText();
				String descricao = fieldDescricaoCad.getText();
				String idVotacaoText = (String) comboBoxNumeroVotacao.getSelectedItem();
				if (idVotacaoText.isBlank()) {
					JOptionPane.showMessageDialog(null, "todos os campos devem estar preenchidos", "Erro",JOptionPane.ERROR_MESSAGE);
					return;
				}
				int id_votacao = Integer.parseInt(idVotacaoText);

				ControllerProposta contVotante = new ControllerProposta();
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

		JButton btnCadVoltar = new JButton("VOLTAR");
		btnCadVoltar.setBounds(684, 570, 109, 23);
		btnCadVoltar.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnCadVoltar, "cell 0 13,alignx center,aligny center");
		btnCadVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ADMCadastro admcadastro = new ADMCadastro();
				admcadastro.setVisible(true);
				dispose();
			}
		});
	}
	public void restaurarIdEleicaoCombobox() {
		try {
			ControllerVotacao contVotacao = new ControllerVotacao();
			ResultSet rs = contVotacao.exibirIdVotacaoProposta();

			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id_votacao"));
				comboBoxNumeroVotacao.addItem(id);
			}
		} catch (SQLException error) {

			JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}

	}

}
