package br.com.votehub.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class ApurarProposta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApurarProposta frame = new ApurarProposta();
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
	public ApurarProposta() {
		setBackground(Color.LIGHT_GRAY);
		setForeground(Color.LIGHT_GRAY);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 778, 603);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Resultado da votação de propostas");
        lblNewLabel.setBounds(5, 5, 820, 26);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
        contentPane.add(lblNewLabel);
        
        table = new JTable();
        table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        table.setDoubleBuffered(true);
        table.setBackground(Color.LIGHT_GRAY);
        table.setBounds(137, 106, 548, 80);
        contentPane.add(table);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(68, 484, 89, 23);
		contentPane.add(btnVoltar);
		btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    			Apuracao apvoto = new Apuracao();
    			apvoto.setVisible(true);
    			dispose();
    		}
    	});
	}
}
