package br.com.votehub.model.criptografia;

import java.sql.SQLException;

import org.jasypt.util.password.StrongPasswordEncryptor;

import br.com.votehub.model.DAOs.DB;

public class Hash {	
	/*Algorithm: SHA-256. o GPT é ?*/
//	Salt size: 16 bytes. Random
//	Iterations: 100000.  
	
	private final static StrongPasswordEncryptor passHash = new StrongPasswordEncryptor();
	
	public static String gerarHash(String senha) {
		String senhaHash = passHash.encryptPassword(senha);
		return senhaHash;
	}
	
	public static boolean verificarHash(String senha, String hash) {
		boolean check = passHash.checkPassword(senha, hash);
		return check;
	
	}
}
