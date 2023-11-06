package br.com.votehub.model.vo;

public class Administrador {
	
	private String usuario;
	private String senha;
	
	public Administrador(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}
	
	
	// autenticação simples a ser refeita
	public boolean login(String usuario, String senha) {
		return usuario.equals(this.usuario) && senha.equals(this.senha);
	}
	
	
}
