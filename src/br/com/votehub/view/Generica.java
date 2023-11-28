package br.com.votehub.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import br.com.votehub.model.DAOs.DB;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
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
		frame.getContentPane().add(lblNewLabel, "cell 3 1,alignx center,aligny bottom");
		
		JComboBox<String> comboBox = new JComboBox<String>();
		frame.getContentPane().add(comboBox, "cell 3 3,growx");
		
		// BOTAO CANCELAR
		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(LoginUsuario::new);
				frame.dispose();
			}
		});
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
        carregarCandidatos(comboBox);

	}
	
	 private void carregarCandidatos(JComboBox<String> comboBox) {
	        Connection conn = null;
	        ResultSet rs = null;
	        PreparedStatement ps = null;
	        try {
	            conn = DB.getConnection();
	            String query = "SELECT nome \r\n FROM candidato \r\n WHERE cargo = ?";
	            ps = conn.prepareStatement(query);
	            String cargoQuery = cargo.toLowerCase();
	            ps.setString(1, cargoQuery);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                String nome = rs.getString("nome");
	                comboBox.addItem(nome);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DB.closeResultSet(rs);
	            DB.closestatement(ps);
	            DB.closeConnection();
	        }
	    }

}
