package br.com.votehub.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import br.com.votehub.model.DAOs.DB;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Generica {

	private JFrame frame;
	private String cargo;
	private JLabel foto;
	private String nomeCand;
	Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
	public Generica(String cargo) {
		this.cargo = cargo;
		initialize();
		
	}

	//  CHAMANDO TELA -> SwingUtilities.invokeLater(() -> new Generica("Diretor"));
	
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
		frame.getContentPane().add(foto, "cell 3 1 1 2");
		
		// BOTAO CANCELAR
		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(LoginUsuario::new);
				frame.dispose();
			}
		});
		
		JLabel candidatoLabel = new JLabel("");
		candidatoLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(candidatoLabel, "cell 3 3,alignx center,aligny bottom");
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("");
		carregarCandidatos(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String candidatoNumero = (String) comboBox.getSelectedItem();
				nomeCandidato(candidatoNumero);
				candidatoLabel.setText(nomeCand);
				ImageIcon imagem = fotoCandidato(candidatoNumero);
				foto.setIcon(imagem);	
			}
		});
		frame.getContentPane().add(comboBox, "cell 3 4,growx");
		
		
		botaoCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(botaoCancelar, "cell 2 6");
		
		// BOTAO AVANCAR
		JButton botaoAvancar = new JButton("Avançar");
		botaoAvancar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		botaoAvancar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().add(botaoAvancar, "cell 4 6");
		
		
        frame.setVisible(true);

	}
	
	 private void carregarCandidatos(JComboBox<String> comboBox) {
	        try {
	            conn = DB.getConnection();
	            String query = "SELECT numero_candidato \r\n FROM candidato \r\n WHERE cargo = ?";
	            ps = conn.prepareStatement(query);
	            String cargoQuery = cargo.toLowerCase();
	            ps.setString(1, cargoQuery);
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
	 private ImageIcon fotoCandidato(String numero) { 	
	        try {
	            conn = DB.getConnection();
	            String query = "SELECT imagem \r\n FROM candidato \r\n WHERE numero_candidato = ?";
	            ps = conn.prepareStatement(query);
	            ps.setString(1, numero);
	            rs = ps.executeQuery();  
	            if (rs.next()) {
	                byte[] imageData = rs.getBytes("imagem");
	                ImageIcon imagem = new ImageIcon(imageData);
	                // Redimensiona a imagem para se ajustar ao JLabel
	                imagem.setImage(imagem.getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_SMOOTH));
	                // Limpa o JLabel antes de definir a nova imagem
	                foto.setIcon(null);
	                foto.setIcon(imagem);
	                return imagem;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.err.println("Erro ao carregar imagem do candidato: " + e.getMessage());
	        } 
			return null;
		 
	 }
	 
	 

}