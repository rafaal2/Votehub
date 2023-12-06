package br.com.votehub.model.vo;

public class RespostaProposta {
	private int id_RespostaProposta;
	private String resposta;
	private int id_Proposta;

	public RespostaProposta(String resposta, int id_Proposta) {
		this.resposta = resposta;
		this.id_Proposta = id_Proposta;
	}

	public int getId_RespostaProposta() {
		return id_RespostaProposta;
	}

	public void setId_RespostaProposta(int id_RespostaProposta) {
		this.id_RespostaProposta = id_RespostaProposta;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public int getId_Proposta() {
		return id_Proposta;
	}

	public void setId_Proposta(int id_Proposta) {
		this.id_Proposta = id_Proposta;
	}
}
