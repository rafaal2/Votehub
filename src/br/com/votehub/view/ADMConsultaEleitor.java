package br.com.votehub.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.votehub.model.vo.Votante;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerVotante;
import br.com.votehub.model.DAOs.DbIntegrityException;
import br.com.votehub.model.DAOs.VotanteDAO;

public class ADMConsultaEleitor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldMatricula;
	private JTextField textFieldNome;
	private JTextField textFieldId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ADMConsultaEleitor frame = new ADMConsultaEleitor();
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
	public ADMConsultaEleitor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][grow][][][][][][][][][grow][][]", "[][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblTitulo = new JLabel("Eleitores");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 17));
		contentPane.add(lblTitulo, "cell 3 0");
		
		JLabel lblMatrícula = new JLabel("Matrícula");
		lblMatrícula.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblMatrícula, "cell 1 2");
		
		textFieldMatricula = new JTextField();
		contentPane.add(textFieldMatricula, "cell 3 2 9 1,growx");
		textFieldMatricula.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblId, "cell 1 4");
		
		textFieldId = new JTextField();
		contentPane.add(textFieldId, "cell 3 4,growx");
		textFieldId.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblNome, "cell 1 6");
		
		textFieldNome = new JTextField();
		contentPane.add(textFieldNome, "cell 3 6 9 1,growx");
		textFieldNome.setColumns(10);
		
		JButton btnConsultar = new JButton("Consultar");
		contentPane.add(btnConsultar, "cell 3 13");
		btnConsultar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String matricula = textFieldMatricula.getText();
		        ControllerVotante controllerVotante = new ControllerVotante();
		        Votante votante = controllerVotante.buscarVotante(matricula);

		        if (votante == null) {
		            JOptionPane.showMessageDialog(null, "Eleitor não encontrado.");
		        } else {
		            textFieldId.setText(Integer.toString(votante.getId_votante()));
		            textFieldNome.setText(votante.getNome());
		        }
		    }
		});

		
		JButton btnDeletar = new JButton("Deletar");
		contentPane.add(btnDeletar, "cell 5 13");
		btnDeletar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            int idVotante = Integer.parseInt(textFieldId.getText());

		            ControllerVotante controller = new ControllerVotante();
		            controller.deletarVotante(idVotante);

		            textFieldMatricula.setText("");
		            textFieldNome.setText("");
		            textFieldId.setText("");

		            JOptionPane.showMessageDialog(null, "Votante deletado com sucesso.");
		        } catch (BusinessException error) {
		            JOptionPane.showMessageDialog(null, "O votante não pode ser deletado pois já votou.");
		        } catch (DbIntegrityException error2) {
		        	JOptionPane.showMessageDialog(null, error2.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		
		JButton btnEditar = new JButton("Editar");
		contentPane.add(btnEditar, "cell 7 13");
		btnEditar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Recupera os dados do votante a partir dos campos de texto
		            int idVotante = Integer.parseInt(textFieldId.getText());
		            String novaMatricula = textFieldMatricula.getText();
		            String novoNome = textFieldNome.getText();
		            // Se estiver usando um campo de senha, adicione também:
		            // String novaSenha = textFieldSenha.getText();

		            // Chama o método para atualizar o votante
		            ControllerVotante controller = new ControllerVotante();
		            controller.atualizarVotante(idVotante, novaMatricula, novoNome);

		            JOptionPane.showMessageDialog(null, "Votante atualizado com sucesso.");
		        } catch (BusinessException ex) {
		            JOptionPane.showMessageDialog(null, ex.getMessage());
		        }
		    }
		});
		
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
