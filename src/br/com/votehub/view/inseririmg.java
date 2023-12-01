package br.com.votehub.view;

import java.sql.Connection;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class inseririmg {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/votehub";
		String usuario = "root";
		String senha = "ADS2023";
		try {
			Connection conexao = DriverManager.getConnection(url, usuario, senha);

			String sql = "SELECT imagem FROM imagensblob WHERE id = ?";
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, 3); 

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				InputStream inputStream = rs.getBinaryStream("imagem");
				FileOutputStream outputStream = new FileOutputStream("C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\21580(2).jpg");

				byte[] buffer = new byte[4096];
				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 1, bytesRead);
				}

				System.out.println("Imagem recuperada com sucesso!");

				outputStream.close();
				inputStream.close();
			} else {
				System.out.println("Imagem não encontrada.");
			}

			pstmt.close();
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
