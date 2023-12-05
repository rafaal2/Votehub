package br.com.votehub.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class ApurarVoto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApurarVoto frame = new ApurarVoto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ApurarVoto() {
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
		contentPane.setLayout(new MigLayout("fill", "[grow][][][][][][][][][][grow][][grow]", "[][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("apurar votos");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPane.add(lblNewLabel, "cell 9 0");
		
		JButton btnNewButton = new JButton("Resultado Diretor");
		contentPane.add(btnNewButton, "cell 9 2,alignx center,aligny center");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ApurarDiretor diretor = new ApurarDiretor();
				diretor.setVisible(true);
				dispose();
			}
		});
		
		JButton btnApurarReitor = new JButton("Resultado Reitor");
		contentPane.add(btnApurarReitor, "cell 9 4,alignx center,aligny center");
		btnApurarReitor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ApurarReitor reitor = new ApurarReitor();
				reitor.setVisible(true);
				dispose();
			}
		});
	}
}
