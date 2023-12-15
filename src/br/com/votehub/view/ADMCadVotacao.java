package br.com.votehub.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

//import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;	

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerCandidato;
import br.com.votehub.controller.ControllerVotacao;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JFormattedTextField;

public class ADMCadVotacao extends JFrame {

	private JPanel contentPane;
	private JTextField fieldNomeCad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ADMCadVotacao frame = new ADMCadVotacao();
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
	public ADMCadVotacao() throws ParseException {
		setTitle("Cadastro de Votação");
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
		panel.setLayout(new MigLayout("fill", "[grow][grow][grow][grow][grow][grow][grow][][][grow][][grow]",
				"[][][][][][][grow][grow][][][][]"));

		JLabel lblCadVotacao = new JLabel("Cadastro de Votação");
		lblCadVotacao.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblCadVotacao, "cell 0 0 12 1,alignx center,aligny center");

		JButton btnVoltarCad = new JButton("VOLTAR");
		btnVoltarCad.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVoltarCad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ADMCadastro admCadastro = new ADMCadastro();
				admCadastro.setVisible(true);
				dispose();
			}
		});

		JLabel lblCadNome = new JLabel("Nome :");
		lblCadNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCadNome, "cell 1 3,alignx trailing");

		fieldNomeCad = new JTextField();
		panel.add(fieldNomeCad, "cell 3 3,growx");
		fieldNomeCad.setColumns(10);

		JLabel lblCadDataInicio = new JLabel("Data e Hora de Início :");
		lblCadDataInicio.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCadDataInicio, "cell 1 4,alignx trailing,aligny baseline");

		JFormattedTextField formattedDataInicio = new JFormattedTextField(new MaskFormatter("##/##/#### ##:##:##"));
		panel.add(formattedDataInicio, "cell 3 4,growx");
		formattedDataInicio
				.setToolTipText("Informe a data e horario de inicio no formato dia/mês/ano hora/minuto/segundo");

		JLabel lblCadDataFinal = new JLabel("Data e Hora de termino :");
		lblCadDataFinal.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCadDataFinal, "cell 1 5,alignx trailing");

		JFormattedTextField formattedDataFim = new JFormattedTextField(new MaskFormatter("##/##/#### ##:##:##"));
		panel.add(formattedDataFim, "cell 3 5,growx");
		formattedDataFim
				.setToolTipText("Informe a data e horario de termino no formato dia/mês/ano hora/minuto/segundo");

		JLabel lblNewLabel = new JLabel("Tipo de Votação :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblNewLabel, "cell 1 7");

		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] { "Candidatos", "Propostas" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		panel.add(list, "cell 3 7,growx,aligny baseline");
		panel.add(btnVoltarCad, "cell 0 11,alignx center,aligny bottom");

		JButton btnNext = new JButton("PRÓXIMO");
		btnNext.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnNext, "cell 11 11,alignx right,aligny bottom");
		btnNext.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				try {

					String nomeVotacao = fieldNomeCad.getText();
					String dataInicioString = formattedDataInicio.getText();
					String dataFimString = formattedDataFim.getText();
					String tipoVotacao = (String) list.getSelectedValue();

					if (nomeVotacao.isEmpty() || dataInicioString.isEmpty() || dataFimString.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Preencha todos os campos antes de cadastrar.", "Erro",JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					if (list.getSelectedValue() != null) {
						tipoVotacao = ((String) list.getSelectedValue()).toLowerCase();
					} else {
						tipoVotacao = "";
					}
					

					Date dataInicio = formato.parse(dataInicioString);
					Date dataFim = formato.parse(dataFimString);

					ControllerVotacao controllerVotacao = new ControllerVotacao();
					controllerVotacao.registrarVotacao(nomeVotacao, dataInicio, dataFim, tipoVotacao);
					JOptionPane.showMessageDialog(null, "Votação cadastrada com sucesso!");

					fieldNomeCad.setText("");
					formattedDataInicio.setText(null);
					formattedDataFim.setText(null);
					list.clearSelection();

				} catch (BusinessException err) {

					JOptionPane.showMessageDialog(null, err.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (ParseException err) {

					JOptionPane.showMessageDialog(null, err.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}

			}

		});

	}

}
