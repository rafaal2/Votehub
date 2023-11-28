package br.com.votehub.model.criptografia;

import org.jasypt.util.text.BasicTextEncryptor;

public class Encriptador {

	private static final String passWD = CriptografiaVotante.obterChaveSecreta();
	BasicTextEncryptor encriptador = new BasicTextEncryptor();
	BasicTextEncryptor desencriptador = new BasicTextEncryptor();

	public String encriptadorDeValores(String senha) {

		encriptador.setPassword(passWD);
		String valorEncrpitografado;
		valorEncrpitografado = encriptador.encrypt(senha);
		return valorEncrpitografado;
	}

	public String desencriptadorDeValores(String senha) {
		String valorDesencriptografado;
		valorDesencriptografado = desencriptador.decrypt(senha);
		return valorDesencriptografado;
	}
}
