package br.com.votehub.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.votehub.controller.ControllerProposta;
import br.com.votehub.model.DAOs.DB;
import net.miginfocom.swing.MigLayout;

public class ApurarProposta extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableResposta;
    private JComboBox<String> comboBoxPropostas;
    private JComboBox<String> comboBoxVotacao;
    private JLabel lblNrRespostas;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
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

    public ApurarProposta() {
        setTitle("Apuração das propostas");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 501, 381);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(164, 247, 176));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("fill", "[grow][][][][][][][][][][grow][][grow]", "[][][][][][][][]"));

        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.menu);
        contentPane.add(panel, "cell 0 0 12 9,alignx center,aligny center");
        panel.setPreferredSize(new Dimension(800, 600));
        panel.setLayout(new MigLayout("fill", "[grow]", "[][][][][][][][][][][][][][][][][][][][][][][][][][]"));

        JLabel lblTitulo = new JLabel("Resultado da votação de propostas");
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 21));
        panel.add(lblTitulo, "cell 0 0, alignx center, wrap");

        lblNrRespostas = new JLabel("Número de respostas cadastradas: ");
        lblNrRespostas.setFont(new Font("Tahoma", Font.BOLD, 12));
        panel.add(lblNrRespostas, "cell 0 1, alignx center, wrap");

        JLabel lblVotacao = new JLabel("Selecione a votação:");
        lblVotacao.setFont(new Font("Tahoma", Font.BOLD, 12));
        panel.add(lblVotacao, "alignx center, split 2");

        comboBoxVotacao = new JComboBox<>();
        panel.add(comboBoxVotacao, "alignx left, wrap");
        comboBoxVotacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restaurarTituloCombobox();
            }
        });

        JLabel lblProposta = new JLabel("Selecione a proposta:");
        lblProposta.setFont(new Font("Tahoma", Font.BOLD, 12));
        panel.add(lblProposta, "alignx center, split 2");

        comboBoxPropostas = new JComboBox<>();
        panel.add(comboBoxPropostas, "alignx left, wrap");
        comboBoxPropostas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                apurarVotosProposta();
                atualizarNumeroRespostas();
            }
        });

        DefaultTableModel tableModel = new DefaultTableModel();

        tableResposta = new JTable(tableModel) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableModel.setColumnIdentifiers(
                new Object[] { "id da proposta", "titulo", "resposta", "numero_respostas_totais" });
        tableResposta.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        tableResposta.setDoubleBuffered(true);
        tableResposta.getColumnModel().getColumn(0).setPreferredWidth(114);
        tableResposta.setBackground(Color.LIGHT_GRAY);
        panel.add(tableResposta, "cell 0 4, grow, span");

        JScrollPane scrollPane = new JScrollPane(tableResposta);
        panel.add(scrollPane, "cell 0 6, grow, span");

        JLabel lblTxtResultado = new JLabel("Resultados");
        lblTxtResultado.setFont(new Font("Tahoma", Font.BOLD, 12));
        panel.add(lblTxtResultado, "cell 0 5, alignx center");

        restaurarvotacaoCombobox();
        
        JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(68, 484, 89, 23);
		panel.add(btnVoltar, "cell 0 17, alignx center");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Apuracao apvoto = new Apuracao();
				apvoto.setVisible(true);
				dispose();
			}
		});
    }

    public void restaurarTituloCombobox() {
        try {
            ControllerProposta objProposta = new ControllerProposta();
            comboBoxPropostas.removeAllItems();
            String idVotacaoSelecionada = (String) comboBoxVotacao.getSelectedItem();

            if (idVotacaoSelecionada != null) {
                ResultSet rs = objProposta.obterTituloPorVotacao(Integer.parseInt(idVotacaoSelecionada));
                while (rs.next()) {
                    String titulo = rs.getString("titulo");
                    if (titulo != null) {
                        comboBoxPropostas.addItem(titulo);
                    }
                }
            }
        } catch (SQLException | NumberFormatException error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void restaurarvotacaoCombobox() {
        try {
            ControllerProposta objProposta = new ControllerProposta();
            comboBoxVotacao.removeAllItems();
            ResultSet rs = objProposta.exibirTitulo();
            while (rs.next()) {
                comboBoxVotacao.addItem(rs.getString("id_votacao"));
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void apurarVotosProposta() {
        DefaultTableModel tableModel = (DefaultTableModel) tableResposta.getModel();
        tableModel.setRowCount(0);
        String propostaSelecionada = (String) comboBoxPropostas.getSelectedItem();
        String idVotacaoSelecionada = (String) comboBoxVotacao.getSelectedItem();

        try {
            Connection conn = DB.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT\r\n" + "    p.id_proposta,\r\n" + "    p.titulo AS pergunta,\r\n"
                    + "    r.resposta,\r\n" + "    COUNT(r.id_respostaproposta) AS numero_respostas_totais\r\n"
                    + "FROM\r\n" + "    proposta p\r\n" + "LEFT JOIN\r\n"
                    + "    respostaproposta r ON p.id_proposta = r.id_proposta\r\n" + "WHERE\r\n"
                    + "    p.titulo = '" + propostaSelecionada + "'\r\n" + "    AND p.id_votacao = "
                    + idVotacaoSelecionada + "\r\n" + "GROUP BY\r\n"
                    + "    p.id_proposta, p.titulo, r.resposta;");

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getString("id_proposta"));
                row.add(rs.getString("pergunta"));
                row.add(rs.getString("resposta"));
                row.add(rs.getInt("numero_respostas_totais"));
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarNumeroRespostas() {
        String propostaSelecionada = (String) comboBoxPropostas.getSelectedItem();
        if (propostaSelecionada != null) {
            try {
                Connection conn = DB.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT\r\n" + "    p.id_proposta,\r\n" + "    p.titulo AS pergunta,\r\n"
                        + "    COUNT(rp.id_respostaproposta) AS numero_respostas\r\n" + "FROM\r\n"
                        + "    proposta p\r\n" + "LEFT JOIN\r\n"
                        + "    respostaproposta rp ON p.id_proposta = rp.id_proposta\r\n" + "WHERE\r\n"
                        + "    p.titulo = '" + propostaSelecionada + "'\r\n" + "GROUP BY\r\n"
                        + "    p.id_proposta, p.titulo;");

                if (rs.next()) {
                    int numeroRespostas = rs.getInt("numero_respostas");
                    lblNrRespostas.setText("Número de respostas cadastradas: " + numeroRespostas);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
