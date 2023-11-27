package br.com.votehub.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaInicial extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaInicial frame = new TelaInicial();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaInicial() {
    	setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("fill", "[][][][grow][][][]", "[][][][][][][][][][][][]"));
                
                        JLabel lblNewLabelVotehub = new JLabel("VoteHub");
                        lblNewLabelVotehub.setHorizontalAlignment(SwingConstants.CENTER);
                        lblNewLabelVotehub.setFont(new Font("Tahoma", Font.BOLD, 34));
                        contentPane.add(lblNewLabelVotehub, "cell 3 0,alignx center,aligny bottom");
                
                        JButton btnNewButtonAcessoUsuario = new JButton("USUÁRIO");
                        btnNewButtonAcessoUsuario.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                LoginUsuario loginUsuario = new LoginUsuario();
                                loginUsuario.setVisible(true);
                                dispose();
                            }
                        });
                        btnNewButtonAcessoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 30));
                        contentPane.add(btnNewButtonAcessoUsuario, "cell 3 4,alignx center");
                
                        JButton btnNewButtonAcessoAdm = new JButton("ADMINISTRADOR");
                        btnNewButtonAcessoAdm.setFont(new Font("Tahoma", Font.PLAIN, 30));
                        contentPane.add(btnNewButtonAcessoAdm, "cell 3 7,alignx center");
        btnNewButtonAcessoAdm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ADMLogin admLogin = new ADMLogin();
                admLogin.setVisible(true);
                dispose();
            }
        });
    }
}

