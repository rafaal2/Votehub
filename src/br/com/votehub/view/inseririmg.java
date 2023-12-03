package br.com.votehub.view;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class inseririmg {

	public static void main(String[] args) {

//		try {
//			Connection conexao = DriverManager.getConnection(url, usuario, senha);
//
//			String sql = "SELECT imagem FROM imagensblob WHERE id = ?";
//			PreparedStatement pstmt = conexao.prepareStatement(sql);
//			pstmt.setInt(1, 3); 
//
//			ResultSet rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				InputStream inputStream = rs.getBinaryStream("imagem");
//				FileOutputStream outputStream = new FileOutputStream("C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\21580(2).jpg");
//
//				byte[] buffer = new byte[4096];
//				int bytesRead = -1;
//
//				while ((bytesRead = inputStream.read(buffer)) != -1) {
//					outputStream.write(buffer, 1, bytesRead);
//				}
//
//				System.out.println("Imagem recuperada com sucesso!");
//
//				outputStream.close();
//				inputStream.close();
//			} else {
//				System.out.println("Imagem não encontrada.");
//			}
//
//			pstmt.close();
//			conexao.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
//	public void addImg() {
//		JFileChooser jfc = new JFileChooser();
//		jfc.setDialogTitle("selecionar arquivo");
//		jfc.setFileFilter(new FileNameExtensionFilter("arquivo de imagens (*.PNG, *.JPG, *.JPEG) ", "png", "jpg", "jpeg"));
//		jfc.showOpenDialog(this);
//	}
}
