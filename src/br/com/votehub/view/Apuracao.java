package br.com.votehub.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

public class Apuracao extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Apuracao() {
		setBackground(Color.LIGHT_GRAY);
		setForeground(Color.LIGHT_GRAY);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setFont(new Font("Tahoma", Font.PLAIN, 13));
		setTitle("Apurar votos\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(164, 247, 176));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setFont(new Font("Tahoma", Font.PLAIN, 13));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("fill", "[grow][][][][][][][][][][grow][][grow]", "[][][][][][][][][]"));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 6 0,alignx center,aligny center");
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(new MigLayout("fill", "[grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow][][grow]", "[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblTitulo = new JLabel("Apuração de resultados");
		panel.add(lblTitulo, "cell 26 1,alignx center");
		lblTitulo.setVerticalAlignment(SwingConstants.TOP);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JButton btnApurarReitor = new JButton("Resultado Reitor");
		panel.add(btnApurarReitor, "cell 26 10,grow");
		btnApurarReitor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ApurarReitor reitor = new ApurarReitor();
				reitor.setVisible(true);
				dispose();
			}
		});
		
		JButton btnApurarProposta = new JButton("Resultado Propostas");
		panel.add(btnApurarProposta, "cell 26 15,grow");
		btnApurarProposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ApurarProposta proposta = new ApurarProposta();
				proposta.setVisible(true);
				dispose();
			}
		});
		
		JButton btnApurarDiretor = new JButton("Resultado Diretor");
		panel.add(btnApurarDiretor, "cell 26 20,grow");
		btnApurarDiretor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ApurarDiretor diretor = new ApurarDiretor();
				diretor.setVisible(true);
				dispose();
			}
		});
		
		JButton btnVoltar = new JButton("VOLTAR");
		panel.add(btnVoltar, "cell 26 40,growx,aligny center");
		btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    			ADMPrincipal admp = new ADMPrincipal();
    			admp.setVisible(true);
    			dispose();
    		}
    	});
	}
}
