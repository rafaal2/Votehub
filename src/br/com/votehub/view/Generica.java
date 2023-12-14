package br.com.votehub.view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;

import br.com.votehub.model.DAOs.CandidatoDAO;
import br.com.votehub.model.DAOs.DB;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Generica {

	private JFrame frame;
	private String cargo;
	private int id_votacao;
	private JLabel foto;
	private String nomeCand;
	private String caminhoImagem;
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	public Generica(String cargo, int id_votacao) {
		this.cargo = cargo;
		this.id_votacao = id_votacao;
		initialize();

	}

	// CHAMANDO TELA -> SwingUtilities.invokeLater(() -> new Generica("Diretor"));

	private void initialize() {
		frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setBounds(100, 100, 450, 300); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("fill", "[][grow][][grow][][grow][]", "[][][][][][][][]"));
		String cargoLabel = cargo.toUpperCase(); 
		JLabel lblNewLabel = new JLabel(cargoLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.getContentPane().add(lblNewLabel, "cell 3 0,alignx center,aligny bottom");

		foto = new JLabel("");
		configurarImagemJLabel(foto, "icons8-câmera-100.png");
		frame.getContentPane().add(foto, "cell 3 1 1 2,alignx center,aligny bottom");
		foto.setMaximumSize(new Dimension(128, 128));

		// BOTAO CANCELAR
		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(TelaLoginVotante::new);
				frame.dispose();
			}
		});

		JLabel candidatoLabel = new JLabel("");
		candidatoLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(candidatoLabel, "cell 3 3,alignx center,aligny bottom");

		JComboBox<String> comboBox = new JComboBox<String>();
		carregarCandidatos(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String candidatoNumero = (String) comboBox.getSelectedItem();
				nomeCandidato(candidatoNumero);
				candidatoLabel.setText(nomeCand);
				Image imagem = exibirFoto(candidatoNumero, foto);
				foto.setIcon(new ImageIcon(imagem));
			}
		});
		frame.getContentPane().add(comboBox, "cell 3 4,growx");

		botaoCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(botaoCancelar, "cell 2 6");

		// BOTAO AVANCAR
		JButton botaoAvancar = new JButton("Avançar");
		botaoAvancar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AuxGenerica auxGenerica = new AuxGenerica();
				guardarInformacoes(comboBox);
				auxGenerica.sequenciaTelas();
				frame.dispose();
			}
		});
		botaoAvancar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(botaoAvancar, "cell 4 6");

		frame.setVisible(true);

	}

	private void carregarCandidatos(JComboBox<String> comboBox) {
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
				nomeCand = nome;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	};

	private Image exibirFoto(String numeroCandidato, JLabel imagem) {
		caminhoImagem = obterCaminhoImagem(numeroCandidato);
		Image rawImage = new ImageIcon(caminhoImagem).getImage();
		Image renderedImage = rawImage.getScaledInstance(imagem.getWidth(), imagem.getHeight(), Image.SCALE_SMOOTH);
		return renderedImage;
	}

	private String obterCaminhoImagem(String numeroCandidato) {
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

	private void guardarInformacoes(JComboBox<String> comboBox) {
		if ("da".equals(cargo)) {
			AuxGenerica.setNumeroCandidatoTela1((String) comboBox.getSelectedItem());
			AuxGenerica.setNomeCandidatoTela1(nomeCand);
			AuxGenerica.setCaminhoImagem1(caminhoImagem);
		} else if ("diretor".equals(cargo)) {
			AuxGenerica.setNumeroCandidatoTela2((String) comboBox.getSelectedItem());
			AuxGenerica.setNomeCandidatoTela2(nomeCand);
			AuxGenerica.setCaminhoImagem2(caminhoImagem);
		} else if ("reitor".equals(cargo)) {
			AuxGenerica.setNumeroCandidatoTela3((String) comboBox.getSelectedItem());
			AuxGenerica.setNomeCandidatoTela3(nomeCand);
			AuxGenerica.setCaminhoImagem3(caminhoImagem);
		}
	}

}