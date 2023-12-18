package br.com.votehub.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerProposta;
import br.com.votehub.controller.ControllerVotacao;
import br.com.votehub.controller.ControllerVotante;
import br.com.votehub.model.DAOs.DbIntegrityException;
import br.com.votehub.model.vo.Proposta;
import br.com.votehub.model.vo.Votacao;
import br.com.votehub.model.vo.Votante;
import net.miginfocom.swing.MigLayout;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.SystemColor;

public class ADMConsultaVotacao extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNomeVotacao;
	private JTextField textFieldIdVotacao;
	private JList<String> list;
    private DefaultListModel<String> listModel;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ADMConsultaVotacao frame = new ADMConsultaVotacao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */
	public ADMConsultaVotacao() throws ParseException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(164, 247, 176));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("fill", "[grow][][][][][][][][][][grow][][grow]", "[][][][][][][][][]"));

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		contentPane.add(panel, "cell 1 0 1 3,alignx center,aligny center");
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(new MigLayout("fill", "[grow][][][][][][][][][][][][][][grow][][grow]",
				"[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));

		JLabel lblTitulo = new JLabel("Votação");
		panel.add(lblTitulo, "cell 5 1,alignx center");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 17));

		JLabel lblIdVotacao = new JLabel("ID");
		lblIdVotacao.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblIdVotacao, "cell 0 3,alignx right");
		lblIdVotacao.setFont(new Font("Tahoma", Font.PLAIN, 13));

		textFieldIdVotacao = new JTextField();
		panel.add(textFieldIdVotacao, "cell 1 3 11 1,growx");
		textFieldIdVotacao.setText("");
		textFieldIdVotacao.setColumns(10);

		JLabel lblNomeVotacao = new JLabel("Nome");
		panel.add(lblNomeVotacao, "cell 0 4,alignx right");
		lblNomeVotacao.setFont(new Font("Tahoma", Font.PLAIN, 13));

		textNomeVotacao = new JTextField();
		panel.add(textNomeVotacao, "cell 1 4 11 1,growx");
		textNomeVotacao.setColumns(10);
		
		listModel = new DefaultListModel<>();
        
        JLabel lblNewLabel = new JLabel("Votações Cadastradas:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        contentPane.add(lblNewLabel, "cell 3 0 1 3");
        list = new JList<>(listModel);
        list.setBackground(SystemColor.menu);
        list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        contentPane.add(list, "cell 4 0 1 3,alignx center,aligny center");
        atualizarListaVotacao();

		JLabel lblDataInicio = new JLabel("Data e hora inicial");
		panel.add(lblDataInicio, "cell 0 5,alignx right");
		lblDataInicio.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JFormattedTextField formattedDataInico = new JFormattedTextField(new MaskFormatter("##/##/#### ##:##:##"));
		panel.add(formattedDataInico, "cell 1 5 11 1,growx");
		formattedDataInico
				.setToolTipText("Informe a data e horario de termino no formato dia/mês/ano hora/minuto/segundo");

		JLabel lblDataFim = new JLabel("Data e hora final");
		panel.add(lblDataFim, "cell 0 7,alignx right");
		lblDataFim.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataFim.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JFormattedTextField formattedDataFim = new JFormattedTextField(new MaskFormatter("##/##/#### ##:##:##"));
		panel.add(formattedDataFim, "cell 1 7 11 1,growx");
		formattedDataFim.setText("");
		formattedDataFim
				.setToolTipText("Informe a data e horario de termino no formato dia/mês/ano hora/minuto/segundo");

		JLabel lblTipoVotacao = new JLabel("Tipo de Votação");
		lblTipoVotacao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoVotacao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblTipoVotacao, "cell 0 9,alignx right");

		JComboBox comboBoxTipoVotacao = new JComboBox();
		comboBoxTipoVotacao.setModel(new DefaultComboBoxModel(new String[] { "candidatos", "propostas" }));
		panel.add(comboBoxTipoVotacao, "cell 1 9 10 1,growx");

		JButton btnConsultar = new JButton("Consultar");
		panel.add(btnConsultar, "cell 2 19");
		btnConsultar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (textFieldIdVotacao.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor, insira o ID da votação para consultar.",
							"Campo em Branco", JOptionPane.WARNING_MESSAGE);
					return;
				}

				try {
					int idVotacao = Integer.parseInt(textFieldIdVotacao.getText());
					String nome = textNomeVotacao.getText();
					ControllerVotacao contVotacao = new ControllerVotacao();
					Votacao vtc = contVotacao.buscarVotacaoId(idVotacao);

					if (vtc == null) {
						JOptionPane.showMessageDialog(null, "Votação não encontrada.");
					} else {
						Date dataInicio = vtc.getData_inicio();
						Date dataFim = vtc.getData_fim();
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						String dataInicioStr = dateFormat.format(dataInicio);
						String dataFimStr = dateFormat.format(dataFim);

						textFieldIdVotacao.setText(Integer.toString(vtc.getId_votacao()));
						textNomeVotacao.setText(vtc.getNome_votacao());
						formattedDataInico.setText(dataInicioStr);
						formattedDataFim.setText(dataFimStr);
						comboBoxTipoVotacao.setSelectedItem(vtc.getTipo_Votacao());
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "ID de votação inválido. Insira um número válido.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro ao buscar a votação.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace(); // Isso pode ser removido em um ambiente de produção
				}
			}
		});

		JButton btnEditar = new JButton("Editar");
		panel.add(btnEditar, "cell 5 19");
		btnEditar.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {

		        try {
		            String idVotacaoStr = textFieldIdVotacao.getText().trim();
		            if (idVotacaoStr.isEmpty() || textNomeVotacao.getText().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Preencha todos os campos antes de editar.");
		                return;
		            }

		            int idVotacao = Integer.parseInt(idVotacaoStr);
		            String nome = textNomeVotacao.getText();
		            String dataInicioStr = formattedDataInico.getText();
		            String dataFimStr = formattedDataFim.getText();
		            String tipoVotacao = (String) comboBoxTipoVotacao.getSelectedItem();

		            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		            Date dataInicio = formato.parse(dataInicioStr);
		            Date dataFim = formato.parse(dataFimStr);

		            ControllerVotacao contVotacao = new ControllerVotacao();
		            contVotacao.atualizarVotacao(idVotacao, nome, dataInicio, dataFim, tipoVotacao);

		            JOptionPane.showMessageDialog(null, "Votação atualizada com sucesso.");
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "ID de votação inválido. Insira um número válido.", "Erro",
		                    JOptionPane.ERROR_MESSAGE);
		        } catch (ParseException | BusinessException error) {
		            JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		        }
		    }

		});

		JButton btnDeletar = new JButton("Deletar");
		panel.add(btnDeletar, "cell 7 19");
		btnDeletar.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {

		        try {
		            String idVotacaoStr = textFieldIdVotacao.getText().trim();
		            if (idVotacaoStr.isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Insira o ID da votação para deletar.");
		                return;
		            }

		            int idVotacao = Integer.parseInt(idVotacaoStr);
		            ControllerVotacao contVotacao = new ControllerVotacao();
		            contVotacao.excluirVotacao(idVotacao);

		            textFieldIdVotacao.setText("");
		            textNomeVotacao.setText("");
		            formattedDataInico.setText("");
		            formattedDataFim.setText("");

		            JOptionPane.showMessageDialog(null, "Votação deletada com sucesso.");
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "ID de votação inválido. Insira um número válido.");
		        } catch (DbIntegrityException error) {
		            JOptionPane.showMessageDialog(null,  "A votação inserida não pode ser excluída pois possui candidatos cadastrados.");
		        } catch (BusinessException error) {
					
		        	JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
		    }

		});

		JButton btnVoltar = new JButton("VOLTAR");
		panel.add(btnVoltar, "cell 0 29");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ADMMenuConsulta consulta = new ADMMenuConsulta();
				consulta.setVisible(true);
				dispose();
			}
		});
	}
	
	 private void atualizarListaVotacao() {
		  
	        listModel.clear();

	        ControllerVotacao controllerProposta = new ControllerVotacao();
	        List<Votacao> votacoes = controllerProposta.ExibirVotacoes();
	        for (Votacao votacao : votacoes) {
	            listModel.addElement("ID : " + votacao.getId_votacao() + " | Titulo : " + votacao.getNome_votacao() + " | Inicio : " + votacao.getData_inicio() + " | Fim : " + votacao.getData_fim());
	        }
	    }
}
