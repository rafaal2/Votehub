package br.com.votehub.view;


import br.com.votehub.controller.ControllerCandidato;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerCandidato;
import br.com.votehub.controller.ControllerVotante;
import br.com.votehub.model.DAOs.CandidatoDAO;
import br.com.votehub.model.DAOs.DbIntegrityException;
import br.com.votehub.model.vo.Candidato;
import net.miginfocom.swing.MigLayout;

public class ADMConsultaCandidato extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNumeroCandidato;
	private JTextField textFieldNome;
	private JTextField textFieldCargo;
	private JTextField textFieldVotacao;
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
					ADMConsultaCandidato frame = new ADMConsultaCandidato();
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
	public ADMConsultaCandidato() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 682, 611);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("",
				"[][][][grow][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][]",
				"[][][][][][][][][][][][][][][][][][][][]"));

		JLabel lblTitulo = new JLabel("Candidatos");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 17));
		contentPane.add(lblTitulo, "cell 10 0");

		JLabel lblNumeroCandidato = new JLabel("N° Candidato");
		lblNumeroCandidato.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblNumeroCandidato, "cell 1 2");

		textFieldNumeroCandidato = new JTextField();
		contentPane.add(textFieldNumeroCandidato, "cell 3 2 30 1,growx");
		textFieldNumeroCandidato.setColumns(10);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblNome, "cell 1 4");

		textFieldNome = new JTextField();
		contentPane.add(textFieldNome, "cell 3 4 30 1,growx");
		textFieldNome.setColumns(10);

		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblCargo, "cell 1 6");

		textFieldCargo = new JTextField();
		contentPane.add(textFieldCargo, "cell 3 6 30 1,growx");
		textFieldCargo.setColumns(10);

		JLabel lblVotacao = new JLabel("ID Votação");
		lblVotacao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblVotacao, "cell 1 8");

		textFieldVotacao = new JTextField();
		contentPane.add(textFieldVotacao, "cell 3 8 30 1,growx");
		textFieldVotacao.setColumns(10);
		URL resource = ADMConsultaCandidato.class.getClassLoader().getResource("icons8-câmera-100.png");

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
		contentPane.add(btnAddImg, "cell 16 10");
		lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon(resource));
		lblImg.setBounds(350, 350, 128, 128);
		getContentPane().add(lblImg, "cell 10 10");
		btnAddImg.setBounds(350, 484, 100, 23);
		getContentPane().add(btnAddImg, "cell 16 10");

		JButton btnConsultar = new JButton("Consultar");
		contentPane.add(btnConsultar, "cell 8 14");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numeroCandidato = textFieldNumeroCandidato.getText();
				ControllerCandidato controllerCandidato = new ControllerCandidato();
				Candidato candidato = controllerCandidato.buscarCandidato(numeroCandidato);

				if (candidato == null) {
					JOptionPane.showMessageDialog(null, "Candidato não encontrado.");
				} else {
					textFieldNome.setText(candidato.getNome());
					textFieldCargo.setText(candidato.getCargo());
					textFieldVotacao.setText(Integer.toString(candidato.getId_votacao()));
//					lblImg.setIcon(
//							new ImageIcon(controllerCandidato.buscarCandidato(numeroCandidato).getImg_candidato()));
					
					exibirImagemNoLabel(lblImg, candidato.getImg_candidato());
				}
			}
		});

		JButton btnEditar = new JButton("Editar");
		contentPane.add(btnEditar, "cell 10 14");
		btnEditar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
		            String numeroCandidato = textFieldNumeroCandidato.getText();
		            String novoNome = textFieldNome.getText();
		            String novoCargo = textFieldCargo.getText();
		            int idVotacao = Integer.parseInt(textFieldVotacao.getText());

		            ControllerCandidato controller = new ControllerCandidato();
		            controller.atualizarCandidato(numeroCandidato, novoNome, novoCargo, idVotacao, img_candidato);

		            JOptionPane.showMessageDialog(null, "Candidato atualizado com sucesso.");
		        } catch (BusinessException ex) {
		            JOptionPane.showMessageDialog(null, ex.getMessage());
		        }
		    }
		});


		JButton btnDeletar = new JButton("Deletar");
		contentPane.add(btnDeletar, "cell 11 14");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String numeroCandidato = textFieldNumeroCandidato.getText();

					ControllerCandidato controller = new ControllerCandidato();
					controller.deletarCandidato(numeroCandidato);

					textFieldNumeroCandidato.setText("");
					textFieldNome.setText("");
					textFieldCargo.setText("");
					textFieldVotacao.setText("");
					lblImg.setIcon(new ImageIcon(resource));

					JOptionPane.showMessageDialog(null, "Candidato deletado com sucesso.");
				} catch (BusinessException error) {
					JOptionPane.showMessageDialog(null, "O candidato não pode ser deletado.");
				} catch (DbIntegrityException error2) {
					JOptionPane.showMessageDialog(null, error2.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton btnVoltar = new JButton("VOLTAR");
		contentPane.add(btnVoltar, "cell 0 19");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaConsulta consulta = new TelaConsulta();
				consulta.setVisible(true);
				dispose();
			}
		});
	}
	
	private void exibirImagemNoLabel(JLabel label, String caminhoImagem) {
        ImageIcon icon = new ImageIcon(caminhoImagem);
        Image imagem = icon.getImage();
        Image novaImagem = imagem.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(novaImagem));
    }

	public void addImg() throws BusinessException, IOException {
	    JFileChooser jfc = new JFileChooser();
	    jfc.setDialogTitle("selecionar arquivo");
	    jfc.setFileFilter(new FileNameExtensionFilter("arquivo de imagens (*.PNG, *.JPG, *.JPEG) ", "png", "jpg", "jpeg"));
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
	


	private void configurarImagemJLabel(JLabel label, String caminhoImagem) {
		URL resource = getClass().getClassLoader().getResource(caminhoImagem);
		if (resource != null) {
			label.setIcon(new ImageIcon(resource));
		} else {
			System.err.println("Imagem não encontrada: " + caminhoImagem);
		}
	}

	private Image exibirImagemCandidatoReitor(String numeroCandidato, JLabel lblimgReitor) {
		String caminhoImagem = obterCaminhoImagemCandidato(numeroCandidato);
		Image rawImage = new ImageIcon(caminhoImagem).getImage();
		Image renderedImage = rawImage.getScaledInstance(lblimgReitor.getWidth(), lblimgReitor.getHeight(),
				Image.SCALE_SMOOTH);
		return renderedImage;
	}

	private Image exibirImagemCandidatoDiretor(String numeroCandidato, JLabel lblimgDiretor) {
		String caminhoImagem = obterCaminhoImagemCandidato(numeroCandidato);
		Image rawImage = new ImageIcon(caminhoImagem).getImage();
		Image renderedImage = rawImage.getScaledInstance(lblimgDiretor.getWidth(), lblimgDiretor.getHeight(),
				Image.SCALE_SMOOTH);
		return renderedImage;
	}

	private String obterCaminhoImagemCandidato(String numeroCandidato) {
		CandidatoDAO candidatoRepository = new CandidatoDAO();
		return candidatoRepository.searchCandidatoImg(numeroCandidato);
	}

}
