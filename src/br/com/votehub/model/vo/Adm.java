package br.com.votehub.model.vo;

public class Adm {

	private int id_adm;
	private String login;
	private String senha;

	public Adm(String login, String senha) {

		this.id_adm = id_adm;
		this.login = login;
		this.senha = senha;
	}
	

	public int getId_adm() {
		return id_adm;
	}

	public void setId_adm(int id_adm) {
		this.id_adm = id_adm;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


}
