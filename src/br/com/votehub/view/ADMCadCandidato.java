package br.com.votehub.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

//import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;	

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerCandidato;
import net.miginfocom.swing.MigLayout;

public class ADMCadCandidato extends JFrame {

	private JPanel contentPane;
	private JTextField fieldNomeCad;
	private JTextField fieldNumCad;
	private JTextField filedCargoCad;
	private JTextField filedIdEleicao;
	private int tamanho;
	private FileInputStream fis;
	private JLabel lblImg;
	private String img_candidato;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ADMCadCandidato frame = new ADMCadCandidato();
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
	public ADMCadCandidato() {
		setTitle("Cadastro de Candidato");
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
		panel.setLayout(null);

		JLabel lblCadCandidato = new JLabel("Cadastro de Candidato");
		lblCadCandidato.setBounds(303, 26, 194, 21);
		lblCadCandidato.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblCadCandidato);

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

		JLabel lblCadNome = new JLabel("Nome :");
		lblCadNome.setBounds(206, 150, 38, 14);
		lblCadNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCadNome);

		fieldNomeCad = new JTextField();
		fieldNomeCad.setBounds(248, 150, 359, 20);
		panel.add(fieldNomeCad);
		fieldNomeCad.setColumns(10);

		JLabel lblCadNumCand = new JLabel("Nº Candidato :");
		lblCadNumCand.setBounds(165, 200, 79, 14);
		lblCadNumCand.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCadNumCand);

		fieldNumCad = new JTextField();
		fieldNumCad.setBounds(248, 200, 359, 20);
		panel.add(fieldNumCad);
		fieldNumCad.setColumns(10);

		JLabel lblCadCargo = new JLabel("Cargo :");
		lblCadCargo.setBounds(205, 250, 39, 14);
		lblCadCargo.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCadCargo);

		filedCargoCad = new JTextField();
		filedCargoCad.setBounds(248, 250, 359, 20);
		panel.add(filedCargoCad);
		filedCargoCad.setColumns(10);

		lblImg = new JLabel("");
		URL resource = ADMCadCandidato.class.getClassLoader().getResource("icons8-câmera-100.png");
		lblImg.setIcon(new ImageIcon(resource));
		lblImg.setBounds(350, 350, 128, 128);
		panel.add(lblImg);
		panel.add(btnVoltarCad);

		JLabel lblCadIdEleicao = new JLabel("Nº da Eleição :");
		lblCadIdEleicao.setBounds(166, 300, 78, 14);
		lblCadIdEleicao.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCadIdEleicao);

		filedIdEleicao = new JTextField();
		filedIdEleicao.setBounds(248, 300, 359, 20);
		panel.add(filedIdEleicao);
		filedIdEleicao.setColumns(10);

		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setBounds(684, 570, 109, 23);
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnCadastrar);

		JButton btnAddImg = new JButton("adicionar foto");
		btnAddImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addImg();
				} catch (BusinessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnAddImg.setBounds(350, 484, 100, 23);
		panel.add(btnAddImg);

		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numero_candidato = fieldNumCad.getText();
				String nomeCandidato = fieldNomeCad.getText();
				String cargoCandidato = filedCargoCad.getText();
				int id_votacao = Integer.parseInt(filedIdEleicao.getText());

				ControllerCandidato contCandidato = new ControllerCandidato();
				try {
					contCandidato.registrarCandidato(numero_candidato, nomeCandidato, cargoCandidato, id_votacao,
							img_candidato);
					JOptionPane.showMessageDialog(null, "Candidato cadastrado com sucesso!");
				} catch (BusinessException error) {
					JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException error2) {
					JOptionPane.showMessageDialog(null, error2.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
	}

	public void addImg() throws BusinessException, IOException {
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("selecionar arquivo");
		jfc.setFileFilter(
				new FileNameExtensionFilter("arquivo de imagens (*.PNG, *.JPG, *.JPEG) ", "png", "jpg", "jpeg"));
		int resultado = jfc.showOpenDialog(this);
		if (resultado == JFileChooser.APPROVE_OPTION) {
			fis = new FileInputStream(jfc.getSelectedFile());
			tamanho = (int) jfc.getSelectedFile().length();
			Image img = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(this.lblImg.getWidth(),
					this.lblImg.getHeight(), Image.SCALE_SMOOTH);
			lblImg.setIcon(new ImageIcon(img));
			lblImg.updateUI();
			img_candidato = jfc.getSelectedFile().getAbsolutePath();
		}

	}
}
