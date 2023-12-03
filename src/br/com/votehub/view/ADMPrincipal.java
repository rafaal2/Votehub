package br.com.votehub.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

public class ADMPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ADMPrincipal frame = new ADMPrincipal();
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
	public ADMPrincipal() {
		setTitle("Menu Principal");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 381);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("fill", "[grow][][][][][][][][][][grow][][grow]", "[][][][][][][][]"));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 5 0 1 3,alignx center,aligny center");
		panel.setPreferredSize(new Dimension(800, 600)); 
		panel.setLayout(new MigLayout("fill", "[grow][][][grow][][grow]", "[][][][][][][][][]"));
		
		JLabel lblMenu = new JLabel("Menu Principal");
		lblMenu.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblMenu, "cell 3 0,alignx center,aligny center");
		
		JButton btnMenuCadastrar = new JButton("CADASTRAR");
		btnMenuCadastrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnMenuCadastrar, "cell 3 1,growx,aligny center");
		btnMenuCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ADMCadastro admCadastro = new ADMCadastro();
                admCadastro.setVisible(true);
                dispose();
            }
        });
		
		JButton btnMenuConsultar = new JButton("CONSULTAR");
		btnMenuConsultar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMenuConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaConsulta consulta = new TelaConsulta();
				consulta.setVisible(true);
				dispose();
			}
		});
		panel.add(btnMenuConsultar, "cell 3 2,growx,aligny center");
		
		JButton btnMenuApurar = new JButton("APURAR VOTOS");
		btnMenuApurar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMenuApurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnMenuApurar, "cell 3 3,growx,aligny center");
		
		JButton btnIniciarVotacao = new JButton("INICIAR VOTAÇÃO");
		btnIniciarVotacao.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnIniciarVotacao, "cell 3 4,growx,aligny center");
		
		JButton btnNewButton = new JButton("VOLTAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaInicial telaInicial = new TelaInicial();
				telaInicial.setVisible(true);
        		dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnNewButton, "cell 3 5,growx,aligny baseline");
	}

}
