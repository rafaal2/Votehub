package br.com.votehub.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

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
import br.com.votehub.controller.ControllerVotante;
import br.com.votehub.model.DAOs.DbIntegrityException;
import br.com.votehub.model.vo.Votante;
import net.miginfocom.swing.MigLayout;
import javax.swing.JFormattedTextField;

public class ADMConsultaVotacao extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNomeVotacao;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
contentPane.setLayout(new MigLayout("", "[][][][grow][][][][][][][][][grow][][]", "[][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblTitulo = new JLabel("Votação");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 17));
		contentPane.add(lblTitulo, "cell 3 0");
		
		JLabel lblNomeVotacao = new JLabel("Nome");
		lblNomeVotacao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblNomeVotacao, "cell 1 2");
		
		textNomeVotacao = new JTextField();
		contentPane.add(textNomeVotacao, "cell 3 2,growx");
		textNomeVotacao.setColumns(10);
		
		
		JLabel lblDataInicio = new JLabel("Data e hora inicial");
		lblDataInicio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblDataInicio, "cell 1 4");
		
		JFormattedTextField formattedDataInico = new JFormattedTextField(new MaskFormatter("##/##/#### ##:##:##"));
		contentPane.add(formattedDataInico, "cell 3 4,growx");
		formattedDataInico.setToolTipText("Informe a data e horario de termino no formato dia/mês/ano hora/minuto/segundo");
		
		
		JLabel lblDataFim = new JLabel("Data e hora final");
		lblDataFim.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataFim.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblDataFim, "cell 1 6");
		
		JFormattedTextField formattedDataFim = new JFormattedTextField(new MaskFormatter("##/##/#### ##:##:##"));
		formattedDataFim.setText("");
		contentPane.add(formattedDataFim, "cell 3 6,growx");
		formattedDataFim.setToolTipText("Informe a data e horario de termino no formato dia/mês/ano hora/minuto/segundo");
		
		
		JButton btnConsultar = new JButton("Consultar");
		contentPane.add(btnConsultar, "cell 3 13");

		
		JButton btnDeletar = new JButton("Deletar");
		contentPane.add(btnDeletar, "cell 5 13");
		
		JButton btnEditar = new JButton("Editar");
		contentPane.add(btnEditar, "cell 7 13");
		
		
		JButton btnVoltar = new JButton("Voltar");
		contentPane.add(btnVoltar, "cell 0 18");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaConsulta consulta = new TelaConsulta();
				consulta.setVisible(true);
				dispose();
			}
		});
	}
}
	


