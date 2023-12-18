package br.com.votehub.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
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
		setTitle("Apurar votos\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(164, 247, 176));
		contentPane.setBorder(new EmptyBorder(5, 20, 20, 20));
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setFont(new Font("Tahoma", Font.PLAIN, 13));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("fill", "[][][][][][][]", "[][][][][][][][][]"));
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panel, "cell 0 1 7 8,grow");
		panel.setLayout(new MigLayout("fill", "[grow][][grow][][grow][][grow]", "[][][][][][][]"));
		
		JLabel lblTitulo = new JLabel("Apuração de Resultados");
		contentPane.add(lblTitulo,  "cell 0 0 7 1,alignx center");
		lblTitulo.setVerticalAlignment(SwingConstants.TOP);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		ImageIcon ar = new ImageIcon("./icons/menu_apurar/apu_cand.png");
		Image arImg = ar.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon resizedAr = new ImageIcon(arImg);
		JButton btnApurarReitor = new JButton("RESULTADO REITOR");
		btnApurarReitor.setIcon(resizedAr);
		btnApurarReitor.setPreferredSize(new Dimension(220, 100));
		btnApurarReitor.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnApurarReitor, "cell 1 1,growx,aligny center");
		btnApurarReitor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				ApurarReitor reitor = new ApurarReitor();
				reitor.setVisible(true);
				dispose();
			}
		});
		
		ImageIcon ap = new ImageIcon("./icons/menu_apurar/apu_pro.png");
		Image apImg = ap.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon resizedAp = new ImageIcon(apImg);
		JButton btnApurarProposta = new JButton("RESULTADO PROPOSTAS");
		btnApurarProposta.setIcon(resizedAp);
		btnApurarProposta.setPreferredSize(new Dimension(220, 100));
		btnApurarProposta.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnApurarProposta, "cell 3 1,growx,aligny center");
		btnApurarProposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				ApurarProposta proposta = new ApurarProposta();
				proposta.setVisible(true);
				dispose();
			}
		});
		
		ImageIcon ad = new ImageIcon("./icons/menu_apurar/apu_cand.png");
		Image adImg = ad.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon resizedAd = new ImageIcon(adImg);
		JButton btnApurarDiretor = new JButton("RESULTADO DIRETOR");
		btnApurarDiretor.setIcon(resizedAd);
		btnApurarDiretor.setPreferredSize(new Dimension(220, 100));
		btnApurarDiretor.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnApurarDiretor, "cell 5 1,growx,aligny center");
		btnApurarDiretor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				ApurarDiretor diretor = new ApurarDiretor();
				diretor.setVisible(true);
				dispose();
			}
		});
		
		JButton espaco = new JButton("CONSULTAR VOTANTE");
		espaco.setPreferredSize(new Dimension(220, 100));
		panel.add(espaco, "cell 1 3,growx,aligny center");
		espaco.setVisible(false);
		
		JButton btnVoltar = new JButton("VOLTAR");
		panel.add(btnVoltar, "cell 1 5,alignx center,aligny center");
		btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    			ADMPrincipal admp = new ADMPrincipal();
    			admp.setVisible(true);
    			dispose();
    		}
    	});
	}
}
