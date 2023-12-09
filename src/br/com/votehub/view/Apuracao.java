package br.com.votehub.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Apuracao extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Apuracao() {
		setBackground(Color.LIGHT_GRAY);
		setForeground(Color.LIGHT_GRAY);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setFont(new Font("Tahoma", Font.PLAIN, 13));
		setTitle("apurar votos\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 381);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("apuração de resultados");
		lblNewLabel.setBounds(284, 24, 228, 27);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPane.add(lblNewLabel);
		
		JButton btnApurarDiretor = new JButton("Resultado Diretor");
		btnApurarDiretor.setBounds(340, 108, 135, 33);
		contentPane.add(btnApurarDiretor);
		btnApurarDiretor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ApurarDiretor diretor = new ApurarDiretor();
				diretor.setVisible(true);
				dispose();
			}
		});
		
		JButton btnApurarReitor = new JButton("Resultado Reitor");
		btnApurarReitor.setBounds(342, 177, 133, 33);
		contentPane.add(btnApurarReitor);
		btnApurarReitor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ApurarReitor reitor = new ApurarReitor();
				reitor.setVisible(true);
				dispose();
			}
		});
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(44, 319, 61, 23);
		contentPane.add(btnVoltar);
		btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    			ADMPrincipal admp = new ADMPrincipal();
    			admp.setVisible(true);
    			dispose();
    		}
    	});
		
		JButton btnApurarProposta = new JButton("Resultado Propostas");
		btnApurarProposta.setBounds(340, 257, 135, 33);
		contentPane.add(btnApurarProposta);
		btnApurarProposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ApurarProposta proposta = new ApurarProposta();
				proposta.setVisible(true);
				dispose();
			}
		});
	}
}
