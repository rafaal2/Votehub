package br.com.votehub.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerVotacao;
import br.com.votehub.controller.ControllerVotante;
import br.com.votehub.model.DAOs.DbIntegrityException;
import br.com.votehub.model.vo.Votacao;
import br.com.votehub.model.vo.Votante;
import net.miginfocom.swing.MigLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ADMConsultaVotacao extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNomeVotacao;
	private JTextField textFieldIdVotacao;

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
	 * @throws ParseException 
	 */
	public ADMConsultaVotacao() throws ParseException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
contentPane.setLayout(new MigLayout("", "[][][][grow][][][][][][][][][grow][][]", "[][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblTitulo = new JLabel("Votação");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 17));
		contentPane.add(lblTitulo, "cell 3 0");
		
		JLabel lblIdVotacao = new JLabel("ID");
		lblIdVotacao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblIdVotacao, "cell 1 4");
		
		textFieldIdVotacao = new JTextField();
		textFieldIdVotacao.setText("");
		contentPane.add(textFieldIdVotacao, "cell 3 4,growx");
		textFieldIdVotacao.setColumns(10);
		
		JLabel lblNomeVotacao = new JLabel("Nome");
		lblNomeVotacao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblNomeVotacao, "cell 1 6");
		
		textNomeVotacao = new JTextField();
		contentPane.add(textNomeVotacao, "cell 3 6,growx");
		textNomeVotacao.setColumns(10);
		
		
		JLabel lblDataInicio = new JLabel("Data e hora inicial");
		lblDataInicio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblDataInicio, "cell 1 8");
		
		JFormattedTextField formattedDataInico = new JFormattedTextField(new MaskFormatter("##/##/#### ##:##:##"));
		contentPane.add(formattedDataInico, "cell 3 8,growx");
		formattedDataInico.setToolTipText("Informe a data e horario de termino no formato dia/mês/ano hora/minuto/segundo");
		
		
		JLabel lblDataFim = new JLabel("Data e hora final");
		lblDataFim.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataFim.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblDataFim, "cell 1 10");
		
		JFormattedTextField formattedDataFim = new JFormattedTextField(new MaskFormatter("##/##/#### ##:##:##"));
		formattedDataFim.setText("");
		contentPane.add(formattedDataFim, "cell 3 10,growx");
		formattedDataFim.setToolTipText("Informe a data e horario de termino no formato dia/mês/ano hora/minuto/segundo");
		
		JLabel lblTipoVotacao = new JLabel("Tipo de Votação");
		lblTipoVotacao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoVotacao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblTipoVotacao, "cell 1 13");
		
		JComboBox comboBoxTipoVotacao = new JComboBox();
		comboBoxTipoVotacao.setModel(new DefaultComboBoxModel(new String[] {"candidatos", "propostas"}));
		contentPane.add(comboBoxTipoVotacao, "cell 3 13,growx");
		
		JButton btnConsultar = new JButton("Consultar");
		contentPane.add(btnConsultar, "cell 1 21");
		btnConsultar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				
				int idVotacao = Integer.parseInt(textFieldIdVotacao.getText());
				String nome = textNomeVotacao.getText();
				ControllerVotacao contVotacao = new ControllerVotacao();
				Votacao vtc = contVotacao.buscarVotacaoId(idVotacao);
				
				Date dataInicio = vtc.getData_inicio();
				Date dataFim = vtc.getData_fim();
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				String dataInicioStr = dateFormat.format(dataInicio);
				String dataFimStr = dateFormat.format(dataFim);
		       			
			if(vtc == null) {				
				
				JOptionPane.showMessageDialog(null, "Votacão não encontrada ou inexistente.");	
				
			} else {
				
				textFieldIdVotacao.setText(Integer.toString(vtc.getId_votacao()));
				textNomeVotacao.setText(vtc.getNome_votacao());
				//datas estão vindo sem hora
				//datas estão vindo sem hora
				//datas estão vindo sem hora
				formattedDataInico.setText(dataInicioStr);
				formattedDataFim.setText(dataFimStr);
				comboBoxTipoVotacao.setSelectedItem(vtc.getTipo_Votacao());
				//datas estão vindo sem hora
				//datas estão vindo sem hora
				//datas estão vindo sem hora
			}
			}
			
		});
		
				
				JButton btnDeletar = new JButton("Deletar");
				contentPane.add(btnDeletar, "cell 3 21");
				btnDeletar.addActionListener(new ActionListener() {

					
					public void actionPerformed(ActionEvent e) {
						
						int idVotacao = Integer.parseInt(textFieldIdVotacao.getText());				
						ControllerVotacao contVotacao = new ControllerVotacao();	
						contVotacao.excluirVotacao(idVotacao);
						
						textFieldIdVotacao.setText("");
						textNomeVotacao.setText("");
						formattedDataInico.setText("");
						formattedDataFim.setText("");
						
						JOptionPane.showMessageDialog(null, "Votação deletada com sucesso.");			
					}
					
				});
		
		JButton btnEditar = new JButton("Editar");
		contentPane.add(btnEditar, "cell 7 21");
		btnEditar.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
				int idVotacao = Integer.parseInt(textFieldIdVotacao.getText());
				String nome = textNomeVotacao.getText();
				String dataInicioStr = formattedDataInico.getText();
				String dataFimStr = formattedDataFim.getText();
				String tipoVotacao = (String) comboBoxTipoVotacao.getSelectedItem();
				
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				
				try {
					
					Date dataInicio = formato.parse(dataInicioStr);
					Date dataFim = formato.parse(dataFimStr);
					
					ControllerVotacao contVotacao = new ControllerVotacao();
					contVotacao.atualizarVotacao(idVotacao, nome, dataInicio, dataFim, tipoVotacao);
					
					JOptionPane.showMessageDialog(null, "Votacao atualizada com sucesso.");					
				} catch (ParseException error) {
					
					JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					//error.printStackTrace();
				} catch (BusinessException error) {
					
					JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					//error.printStackTrace();
				}
				
			}
			
		});
		
		JButton btnVoltar = new JButton("VOLTAR");
		contentPane.add(btnVoltar, "cell 0 26");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ADMMenuConsulta consulta = new ADMMenuConsulta();
				consulta.setVisible(true);
				dispose();
			}
		});
	}
}
	


