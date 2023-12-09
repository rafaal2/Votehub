package br.com.votehub.model.vo;

public class Candidato {

	private String numero_candidato;
	private String nome;
	private String cargo;
	private int id_votacao;
	private String img_candidato;

	public Candidato(String numero_candidato, String nome, String cargo, int id_votacao, String img_candidato) {
		super();
		this.numero_candidato = numero_candidato;
		this.nome = nome;
		this.cargo = cargo;
		this.id_votacao = id_votacao;
		this.img_candidato = img_candidato;
	}

	public String getImg_candidato() {
		return img_candidato;
	}

	public void setImg_candidato(String img_candidato) {
		this.img_candidato = img_candidato;
	}

	public int getId_votacao() {
		return id_votacao;
	}

	public void setId_votacao(int id_votacao) {
		this.id_votacao = id_votacao;
	}

	public String getNumero_candidato() {
		return numero_candidato;
	}

	public void setNumero_candidato(String numero_candidatp) {
		this.numero_candidato = numero_candidatp;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

}
