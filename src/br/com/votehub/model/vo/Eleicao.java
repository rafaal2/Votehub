package br.com.votehub.model.vo;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class Eleicao {
	
	private int idEleicao;
	private Date data;
	private String nome;
	private ArrayList<Votante> votantes;
	private ArrayList<Candidato> candidatos;
	
	public Eleicao(int idEleicao, String data, String nome) {
		this.idEleicao = idEleicao;
		this.nome = nome;
		this.votantes = new ArrayList<>();
		this.candidatos = new ArrayList<>();
		setData(data);
 	}

	public int getIdEleicao() {
		return idEleicao;
	}

	public void setIdEleicao(int idEleicao) {
		this.idEleicao = idEleicao;
	}

	public Date getData() {
		return data;
	}

	public void setData(String dataStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-3"));
        try {
            Date data = sdf.parse(dataStr);
            this.data = data;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {

		this.nome = nome;
	}
	
	public void adicionarVotante(Votante votante) {
	    votantes.add(votante);
	}

	public void adicionarCandidato(Candidato candidato) {
	    candidatos.add(candidato);
	}

	public void removerVotante(Votante votante) {
	    votantes.remove(votante);
	}

	public void removerCandidato(Candidato candidato) {
	    candidatos.remove(candidato);
	}

	public ArrayList<Votante> getVotantes() {
	    return votantes;
	}

	public ArrayList<Candidato> getCandidatos() {
	    return candidatos;
	}

	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    String dataPadraoBr = sdf.format(data);
	    return "Eleição: " + nome + " | Data: " + dataPadraoBr;
	}
	
	
}
