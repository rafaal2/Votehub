package br.com.votehub.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

//import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;	

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerCandidato;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class CADEleicao extends JFrame {

	private JPanel contentPane;
	private JTextField fieldNomeCad;
	private JTextField fieldNumCad;
	private JTextField filedCargoCad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CADEleicao frame = new CADEleicao();
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
	public CADEleicao() {
		setTitle("Cadastro de Eleição");
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
		panel.setLayout(new MigLayout("fill", "[grow][][grow][grow][grow][grow][grow][][][grow][][grow]", "[][][][][][][grow][grow][][][][]"));
		
		JLabel lblCadEleicao = new JLabel("Cadastro de Eleição");
		lblCadEleicao.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblCadEleicao, "cell 0 0 12 1,alignx center,aligny center");
		
		JButton btnVoltarCad = new JButton("VOLTAR");
		btnVoltarCad.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVoltarCad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblCadNome = new JLabel("Nome :");
		lblCadNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCadNome, "cell 1 3,alignx trailing");
		
		fieldNomeCad = new JTextField();
		panel.add(fieldNomeCad, "cell 3 3 7 1,growx");
		fieldNomeCad.setColumns(10);
		
		JLabel lblCadDataInicio = new JLabel("Data de Início :");
		lblCadDataInicio.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCadDataInicio, "cell 1 4,alignx trailing,aligny baseline");
		
		fieldNumCad = new JTextField();
		panel.add(fieldNumCad, "cell 3 4 7 1,growx");
		fieldNumCad.setColumns(10);
		
		JLabel lblCadDataFinal = new JLabel("Data de Término :");
		lblCadDataFinal.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCadDataFinal, "cell 1 5,alignx trailing");
		
		filedCargoCad = new JTextField();
		panel.add(filedCargoCad, "cell 3 5 7 1,growx");
		filedCargoCad.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tipo de Votação :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblNewLabel, "cell 1 7");
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Diretor ", "Reitor", "Ambos"};
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
		
		
	}

}
