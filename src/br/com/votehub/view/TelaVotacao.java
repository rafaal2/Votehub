package br.com.votehub.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import br.com.votehub.controller.BusinessException;
import br.com.votehub.controller.ControllerCandidato;
import br.com.votehub.controller.ControllerVotacaoVotante;
import br.com.votehub.controller.ControllerVoto;
import br.com.votehub.model.DAOs.CandidatoDAO;
import br.com.votehub.model.vo.Votante;
import net.miginfocom.swing.MigLayout;

public class TelaVotacao {

	private JFrame frame;
	String ano = Integer.toString(LocalDate.now().getYear());
	private JButton botaoCancelar;
	private JButton botaoAvancar;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	// private JLabel lblNewLabel_3;
	private JComboBox[] comboBoxes_ = new JComboBox[3];
	// CHAMANDO A TELA -> SwingUtilities.invokeLater(TelaVotacao::new);
	private Votante vtt;
	private JLabel lblimgReitor;
	private JLabel lblimgDiretor;

	public TelaVotacao(Votante vtt) {
		this.vtt = vtt;
		try {
			initialize();
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setVisible(true);
			restaurarReitorCombobox();
			restaurarDiretorCombobox();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao inicializar a Tela de Votação: " + e.getMessage());
		}
	}

	private int contarComboBoxes() {
		int count = 0;
		for (java.awt.Component component : frame.getContentPane().getComponents()) {
			if (component instanceof JComboBox) {
				count++;
			}
		}
		return count;
	}

	private void initialize() {
		frame = new JFrame();
		frame.setForeground(Color.GREEN);
		frame.getContentPane().setForeground(Color.GREEN);
		frame.setBounds(100, 100, 625, 427);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(164, 247, 176));
		frame.getContentPane()
				.setLayout(new MigLayout("fill", "[][][grow][][][][][][][][][20%,grow][grow][][grow][][]", "[][][][][][][][]"));
		
				lblNewLabel = new JLabel("REITOR:");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
				frame.getContentPane().add(lblNewLabel, "cell 10 2,alignx center");

		lblimgReitor = new JLabel("");
		configurarImagemJLabel(lblimgReitor, "icons8-câmera-100.png");
		lblimgReitor.setBorder(new LineBorder(Color(164, 247, 176), 3));
		frame.getContentPane().add(lblimgReitor, "cell 11 2,alignx trailing");

		comboBox = new JComboBox();
		comboBoxes_[0] = comboBox;
		frame.getContentPane().add(comboBox, "cell 12 2,growx");
		
				lblNewLabel_1 = new JLabel("DIRETOR:");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
				frame.getContentPane().add(lblNewLabel_1, "cell 10 3,alignx center");

		lblimgDiretor = new JLabel("");
		configurarImagemJLabel(lblimgDiretor, "icons8-câmera-100.png");
		lblimgDiretor.setBorder(new LineBorder(Color(164, 247, 176), 3));
		frame.getContentPane().add(lblimgDiretor, "cell 11 3,alignx trailing");

		lblimgReitor.setMaximumSize(new Dimension(100, 100));
		lblimgDiretor.setMaximumSize(new Dimension(100, 100));

		comboBox_1 = new JComboBox();
		comboBoxes_[1] = comboBox_1;
		frame.getContentPane().add(comboBox_1, "cell 12 3,growx");

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numeroCandidato = (String) comboBox.getSelectedItem();
				Image imagemReitor = exibirImagemCandidatoReitor(numeroCandidato, lblimgReitor);
				lblimgReitor.setIcon(new ImageIcon(imagemReitor));
			}
		});

		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numeroCandidato = (String) comboBox_1.getSelectedItem();
				Image imagemDiretor = exibirImagemCandidatoDiretor(numeroCandidato, lblimgDiretor);
				lblimgDiretor.setIcon(new ImageIcon(imagemDiretor));
			}
		});

		botaoAvancar = new JButton("Avançar");
		botaoAvancar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botaoAvancar.setHorizontalAlignment(SwingConstants.RIGHT);
//				String numeroBusca = (String) comboBox.getSelectedItem();
//				ControllerVotacao contVotacao = new ControllerVotacao();
//				
//				try {
//					
//					contVotacao.checarInicio(numeroBusca);
//					contVotacao.checarTermino(numeroBusca);
//					
//				} catch (BusinessException error) {
//					
//					JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
//					return;
//				}

		botaoAvancar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cargoReitor = "Reitor";
				String nmrReitor = (String) comboBox.getSelectedItem();

				String cargoDiretor = "Diretor";
				String nmrDiretor = (String) comboBox_1.getSelectedItem();

				String mensagem = "Cargo: " + cargoReitor + "\nNumero do Candidato: " + nmrReitor + "\n\n";
				mensagem += "Cargo: " + cargoDiretor + "\nNumero do Candidato: " + nmrDiretor + "\n\n";
				mensagem += "Confirma o voto?";

				int opcao = JOptionPane.showConfirmDialog(frame, mensagem, "Revisão de Voto",
						JOptionPane.YES_NO_OPTION);

				if (opcao == JOptionPane.YES_OPTION) {
					ControllerVotacaoVotante cvv = new ControllerVotacaoVotante();
					try {
						cvv.registrarVotacaoVotante(1, vtt.getId_votante());
					} catch (BusinessException error) {
						JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						return;
					}

					int n_votos = contarComboBoxes();
					for (int i = 0; i < n_votos; i++) {
						String numerocandidato = (String) comboBoxes_[i].getSelectedItem();
						try {
							ControllerVoto contVoto = new ControllerVoto();
							contVoto.registrarVoto(numerocandidato);
						} catch (BusinessException error) {
							JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}
					}
					JOptionPane.showMessageDialog(null, "Voto cadastrado com sucesso!");
					frame.dispose();

		            TelaSelectVotacao telaSelectVotacao = new TelaSelectVotacao(vtt);
		            telaSelectVotacao.setVisible(true);

				} else {

				}
			}
		});

		botaoCancelar = new JButton("Cancelar");
		botaoCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botaoCancelar.setHorizontalAlignment(SwingConstants.LEFT);
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaSelectVotacao admPrincipal = new TelaSelectVotacao(vtt);
				admPrincipal.setVisible(true);
				dispose();
			}
		});

		frame.getContentPane().add(botaoCancelar, "flowx,cell 4 7,alignx center,aligny baseline");
		frame.getContentPane().add(botaoAvancar, "cell 12 7,alignx center,aligny baseline");
	}

	private Color Color(int i, int j, int k) {
		// TODO Auto-generated method stub
		return null;
	}

	protected void dispose() {
		// TODO Auto-generated method stub
	}

	public void restaurarReitorCombobox() {
		try {
			ControllerCandidato objCandidato = new ControllerCandidato();
			ResultSet rs = objCandidato.exibirReitor();

			while (rs.next()) {
				comboBoxes_[0].addItem(rs.getString("numero_candidato"));
			}
		} catch (SQLException error) {

			JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void restaurarDiretorCombobox() {
		try {
			ControllerCandidato objCandidato = new ControllerCandidato();
			ResultSet rs = objCandidato.exibirDiretor();

			while (rs.next()) {
				comboBoxes_[1].addItem(rs.getString("numero_candidato"));
			}
		} catch (SQLException error) {

			JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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

	private void configurarImagemJLabel(JLabel label, String caminhoImagem) {
		URL resource = getClass().getClassLoader().getResource(caminhoImagem);
		if (resource != null) {
			label.setIcon(new ImageIcon(resource));
		} else {
			System.err.println("Imagem não encontrada: " + caminhoImagem);
		}
	}

	public static void setVisible(boolean b) {
	}

}