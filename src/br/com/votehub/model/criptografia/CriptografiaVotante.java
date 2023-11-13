package br.com.votehub.model.criptografia;

public class CriptografiaVotante {
	private static final String NOME_VARIAVEL_AMBIENTE = "USERNAME";

    public static String obterChaveSecreta() {
        String chave = System.getenv(NOME_VARIAVEL_AMBIENTE);
        if (chave == null) {
            throw new RuntimeException("A variável de ambiente " + NOME_VARIAVEL_AMBIENTE + " não está configurada.");
        }
        return chave;
    }

}
