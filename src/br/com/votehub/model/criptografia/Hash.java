package br.com.votehub.model.criptografia;



import org.jasypt.util.password.StrongPasswordEncryptor;



public class Hash {	
	/*Algorithm: SHA-256. o GPT é ?*/
//	Salt size: 16 bytes. Random
//	Iterations: 100000.  
	
	private final static StrongPasswordEncryptor passHash = new StrongPasswordEncryptor();
	
	public static String gerarHash(String senha) { 
		String senhaHash = passHash.encryptPassword(senha);  
		return senhaHash;
	}
	
}
