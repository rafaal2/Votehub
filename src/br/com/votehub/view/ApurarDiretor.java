package br.com.votehub.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.votehub.model.DAOs.DB;
import javax.swing.JButton;

public class ApurarDiretor extends JFrame {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement stt = null;
    PreparedStatement stt1 = null;

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ApurarDiretor frame = new ApurarDiretor();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ApurarDiretor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 846, 413);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Resultado Eleição de Diretor");
        lblNewLabel.setBounds(5, 5, 820, 26);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
        contentPane.add(lblNewLabel);

        DefaultTableModel tableModel = new DefaultTableModel();

        table = new JTable(tableModel);
        tableModel.setColumnIdentifiers(new Object[]{"Número do Candidato", "Nome", "Votos", "Foto"});
        table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        table.setDoubleBuffered(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(114);
        table.setBackground(Color.LIGHT_GRAY);
        table.setBounds(137, 106, 548, 80);
        contentPane.add(table);

        apurarVotosDiretor();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(137, 106, 548, 80);
        contentPane.add(scrollPane);
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(10, 300, 89, 23);
        contentPane.add(btnVoltar);
        btnVoltar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
			Apuracao apvoto = new Apuracao();
			apvoto.setVisible(true);
			dispose();
		}
	});

    }

    public void apurarVotosDiretor() {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        try {
            conn = DB.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("SELECT\r\n"
                    + "	  candidato.numero_candidato,\r\n"
                    + "	  candidato.nome AS nome_candidato,\r\n"
                    + "	  COUNT(voto.id_voto) AS numero_de_votos\r\n"
                    + "	  FROM\r\n"
                    + "		    candidato\r\n"
                    + "	  LEFT JOIN\r\n"
                    + "		    voto ON candidato.numero_candidato = voto.numero_candidato\r\n"
                    + "	  WHERE\r\n"
                    + "		    candidato.cargo = 'Diretor'\r\n"
                    + "	  GROUP BY\r\n"
                    + "		    candidato.numero_candidato, candidato.nome\r\n"
                    + "	  ORDER BY\r\n"
                    + "		    numero_de_votos DESC;");

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getString("numero_candidato"));
                row.add(rs.getString("nome_candidato"));
                row.add(rs.getInt("numero_de_votos"));
                row.add("");
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeResultSet(rs);
            DB.closestatement(st);
        }
    }
}