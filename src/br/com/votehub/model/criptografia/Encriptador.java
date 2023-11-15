package br.com.votehub.model.criptografia;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import org.jasypt.util.text.BasicTextEncryptor;

public class Encriptador {

	private static final String passWD = CriptografiaVotante.obterChaveSecreta();

	public String encriptadorDeValores(String nome, String operacao) {

		BasicTextEncryptor encriptadorDecriptador = new BasicTextEncryptor();
		encriptadorDecriptador.setPassword(passWD);
		if (operacao.equals("C")) {
			String valorEncrpitografado;
			valorEncrpitografado = encriptadorDecriptador.encrypt(nome);
			return valorEncrpitografado;
		} else {
			String valorDesencriptografado;
			valorDesencriptografado = encriptadorDecriptador.decrypt(nome);
			return valorDesencriptografado;
		}

	}
}
