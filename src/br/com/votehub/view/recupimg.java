package br.com.votehub.view;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class recupimg {

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            JFrame frame = new JFrame("Exibição de Imagem");
	            frame.setSize(1200, 1200);
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	            // Criar um JLabel para exibir a imagem
	            JLabel label = new JLabel();

	            // Carregar a imagem do arquivo
	            ImageIcon icon = carregarImagem("C:\\\\Users\\\\rafae\\\\Pictures\\\\Screenshots\\\\Captura de Tela (60).png");

	            // Definir o ícone no JLabel
	            label.setIcon(icon);

	            // Adicionar o JLabel ao painel de conteúdo do JFrame
	            frame.getContentPane().add(label, BorderLayout.CENTER);

	            // Tornar o JFrame visível
	            frame.setVisible(true);
	        });
	    }

	    private static ImageIcon carregarImagem(String caminho) {
	        try {
	            BufferedImage image = ImageIO.read(new File(caminho));
	            return new ImageIcon(image);
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	}


