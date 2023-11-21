package br.com.votehub.model.criptografia;

import java.security.MessageDigest;
import javax.crypto.Cipher;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

public class Encriptador {

	private static final String passWD = CriptografiaVotante.obterChaveSecreta();

	public String encriptadorDeValores(String senha, String operacao) {

		BasicTextEncryptor encriptadorDecriptador = new BasicTextEncryptor();
		encriptadorDecriptador.setPassword(passWD);
		if (operacao.equals("C")) {
			String valorEncrpitografado;
			valorEncrpitografado = encriptadorDecriptador.encrypt(senha);
			return valorEncrpitografado;
		} else {
			String valorDesencriptografado;
			valorDesencriptografado = encriptadorDecriptador.decrypt(senha);
			return valorDesencriptografado;
			
		}
	}
	private static final String passhash = CriptografiaVotante.obterChaveSecreta();
	private final static StrongPasswordEncryptor passHash = new StrongPasswordEncryptor();
	public static boolean verificarsenha(String senhadigit, String senha) {
		boolean check = passHash.checkPassword(senhadigit, senha);
		return check;
	}
}
