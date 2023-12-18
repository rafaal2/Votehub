package br.com.votehub.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import br.com.votehub.model.DAOs.DB;
import br.com.votehub.model.vo.Votante;
import net.miginfocom.swing.MigLayout;

public class TelaVotacao2 {

	private JFrame frame;
	String ano = Integer.toString(LocalDate.now().getYear());
	private JButton botaoCancelar;
	private JButton botaoAvancar;
	// private JLabel lblNewLabel_3;
	private JComboBox[] comboBoxes_ = new JComboBox[3];
	// CHAMANDO A TELA -> SwingUtilities.invokeLater(TelaVotacao::new);
	private Votante vtt;
	private int idVotacao;
	private JLabel imagem1;
	private JLabel imagem2;
	private JLabel nomeCandidato1;
	private JLabel nomeCandidato2;
	private JLabel cargo1;
	private JLabel cargo2;
	private JComboBox<String> comboBox1;
	private JComboBox<String> comboBox2;
	private boolean diretor;
	private boolean reitor;
	private boolean fechar;
	private String mensagem;
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	public TelaVotacao2(Votante vtt, int idVotacao) {
		this.vtt = vtt;
		this.idVotacao = idVotacao;
		try {
			initialize();
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao inicializar a Tela de Votação: " + e.getMessage());
		}
	}

	private void initialize() {
		frame = new JFrame();
		frame.setForeground(Color.GREEN);
		frame.getContentPane().setForeground(Color.GREEN);
		frame.setBounds(100, 100, 625, 427);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(164, 247, 176));
		frame.getContentPane().setLayout(
				new MigLayout("fill", "[][][grow][][][][][][][][][20%,grow][grow][][grow][][]", "[][][][][][][][]"));

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

		obterCargos(idVotacao);
		if (diretor && reitor) {
			cargo1("diretor", idVotacao);
			cargo2(idVotacao);
		} else if(diretor) {
			cargo1("diretor", idVotacao);
		} else if (reitor) {
			cargo1("reitor", idVotacao);
		}

		botaoAvancar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (diretor && reitor) {
					mensagem = "Cargo: " + "Diretor" + "\nNumero do Candidato: " + (String) comboBox1.getSelectedItem()
							+ "\n\n";
					mensagem += "Cargo: " + "Reitor" + "\nNumero do Candidato: " + (String) comboBox2.getSelectedItem()
							+ "\n\n";
				} else if (diretor) {
					mensagem = "Cargo: " + "Diretor" + "\nNumero do Candidato: " + (String) comboBox1.getSelectedItem()
							+ "\n\n";
				} else if (reitor) {
					mensagem = "Cargo: " + "Reitor" + "\nNumero do Candidato: " + (String) comboBox1.getSelectedItem()
							+ "\n\n";
				}
				mensagem += "Confirma o voto?";

				int opcao = JOptionPane.showConfirmDialog(frame, mensagem, "Revisão de Voto",
						JOptionPane.YES_NO_OPTION);

				if (opcao == JOptionPane.YES_OPTION) {
					ControllerVotacaoVotante cvv = new ControllerVotacaoVotante();
					try {
						cvv.registrarVotacaoVotante(idVotacao, vtt.getId_votante());
					} catch (BusinessException error) {
						JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						return;
					}

					int n_votos = contarComboBoxes();
					for (int i = 0; i < n_votos; i++) {
						if (comboBoxes_[i] != null) {
							String numerocandidato = (String) comboBoxes_[i].getSelectedItem();
							try {
								ControllerVoto contVoto = new ControllerVoto();
								contVoto.registrarVoto(numerocandidato);
							} catch (BusinessException error) {
								JOptionPane.showMessageDialog(null, error.getMessage(), "Erro",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					JOptionPane.showMessageDialog(null, "Voto cadastrado com sucesso!");
					frame.dispose();

//					TelaSelectVotacao telaSelectVotacao = new TelaSelectVotacao(vtt);
//					telaSelectVotacao.setVisible(true);

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

	public void dispose() {
		frame.dispose();
	}

	private Image exibirImagemCandidato(String numeroCandidato, JLabel lblimgReitor) {
		String caminhoImagem = obterCaminhoImagemCandidato(numeroCandidato);
		Image rawImage = new ImageIcon(caminhoImagem).getImage();
		Image renderedImage = rawImage.getScaledInstance(lblimgReitor.getWidth(), lblimgReitor.getHeight(),
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

	private void carregarCandidatos(JComboBox<String> comboBox, String cargo, int id_votacao) {
		try {
			conn = DB.getConnection();
			String query = "SELECT numero_candidato \r\n FROM candidato \r\n WHERE cargo = ? AND id_votacao = ?";
			ps = conn.prepareStatement(query);
			String cargoQuery = cargo.toLowerCase();
			ps.setString(1, cargoQuery);
			ps.setInt(2, id_votacao);
			rs = ps.executeQuery();
			while (rs.next()) {
				String numero = rs.getString("numero_candidato");
				comboBox.addItem(numero);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private String nomeCandidato(String numero) {
		try {
			conn = DB.getConnection();
			String query = "SELECT nome \r\n FROM candidato \r\n WHERE numero_Candidato = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, numero);
			rs = ps.executeQuery();
			while (rs.next()) {
				String nome = rs.getString("nome");
				return nome;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	};

	private int contarComboBoxes() {
		int count = 0;
		for (java.awt.Component component : frame.getContentPane().getComponents()) {
			if (component instanceof JComboBox) {
				count++;
			}
		}
		return count;
	}

	public void obterCargos(int id) {
		try {
			conn = DB.getConnection();
			String query = "SELECT DISTINCT cargo \r\n FROM candidato \r\n WHERE id_votacao = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				String temp = rs.getString("cargo");
				if ("diretor".equals(temp.toLowerCase())) {
					diretor = true;
				} else if ("reitor".equals(temp.toLowerCase())) {
					reitor = true;
				}
			}
			if(!diretor && !reitor) {
	            fechar = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void cargo1(String cargo, int id_votacao) {
		cargo1 = new JLabel(cargo.toUpperCase() + ":");
		cargo1.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(cargo1, "cell 7 2,alignx center");
		imagem1 = new JLabel("");
		imagem1.setHorizontalAlignment(SwingConstants.LEFT);
		configurarImagemJLabel(imagem1, "icons8-câmera-100.png");
		imagem1.setBorder(new LineBorder(Color(164, 247, 176), 3));
		frame.getContentPane().add(imagem1, "cell 9 2,alignx trailing");
		nomeCandidato1 = new JLabel();
		nomeCandidato1.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(nomeCandidato1, "cell 10 2,growx");

		this.comboBox1 = new JComboBox<String>();
		carregarCandidatos(comboBox1, cargo, id_votacao);
		frame.getContentPane().add(comboBox1, "cell 12 2,growx 5000");
		comboBoxes_[0] = comboBox1;
		comboBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numeroCandidato = (String) comboBox1.getSelectedItem();
				nomeCandidato1.setText(nomeCandidato(numeroCandidato));
				Image imagem = exibirImagemCandidato(numeroCandidato, imagem1);
				imagem1.setIcon(new ImageIcon(imagem));
				imagem1.setMaximumSize(new Dimension(100, 100));
			}
		});
	}

	public void cargo2(int id_votacao) {
		cargo2 = new JLabel("REITOR: ");
		cargo2.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(cargo2, "cell 7 3,alignx center");
		imagem2 = new JLabel("");
		configurarImagemJLabel(imagem2, "icons8-câmera-100.png");
		imagem2.setBorder(new LineBorder(Color(164, 247, 176), 3));
		frame.getContentPane().add(imagem2, "cell 9 3,alignx trailing");
		imagem2.setMaximumSize(new Dimension(100, 100));
		nomeCandidato2 = new JLabel();
		frame.getContentPane().add(nomeCandidato2, "cell 10 3,alignx center");

		this.comboBox2 = new JComboBox<String>();
		carregarCandidatos(comboBox2, "reitor", id_votacao);
		frame.getContentPane().add(comboBox2, "cell 12 3,growx");
		comboBoxes_[1] = comboBox2;
		comboBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numeroCandidato = (String) comboBox2.getSelectedItem();
				nomeCandidato2.setText(nomeCandidato(numeroCandidato));
				Image imagem = exibirImagemCandidato(numeroCandidato, imagem2);
				imagem2.setIcon(new ImageIcon(imagem));
				imagem2.setMaximumSize(new Dimension(100, 100));
			}
		});

	}
}
