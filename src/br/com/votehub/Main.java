package br.com.votehub;

import java.util.Scanner;

import br.com.votehub.model.DAOs.votanteDAO;
import br.com.votehub.model.criptografia.CriptografiaVotante;
import br.com.votehub.model.criptografia.Encriptador;
import br.com.votehub.model.vo.Votante;

public class Main {

	public static void main(String[] args) {
		System.out.println(CriptografiaVotante.obterChaveSecreta());

		Main programa = new Main();
		programa.addVotante();
	}
	
	private void addVotante() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Digite o id do votante: ");
		int id = sc.nextInt();
		System.out.print("Digite o nome do votante: ");
		String nome = sc.next();
		System.out.print("Digite o cpf do votante: ");
		String cpf = sc.next();
		System.out.print("Digite a ocupação do votante: ");
		String ocupacao = sc.next();

		Votante v = new Votante(id, nome, cpf, ocupacao);

		votanteDAO vdao = new votanteDAO();
		vdao.addVotante(v);
		sc.close();
	}
	
	/*private void addCandidato() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nome do candidato:");
		String nome = sc.next();
		System.out.println("ID do candidato:");
		int id = sc.nextInt();
		System.out.println("Cargo do candidato:");
		String cargo = sc.next();
		
		Candidato c = new Candidato(nome, id, cargo);
		candidatoDAO cdao = new candidatoDAO();
		cdao.addCandidato(c);
		sc.close();
	}

	 private void addVoto() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Digite o id do voto: ");
		int id = sc.nextInt();
		System.out.print("Digite o id do candidato a ser votado: ");
		int id_candidato = sc.nextInt();
		System.out.print("Digite o id do vontate que vai votar: ");
		int id_votante = sc.nextInt();

		Voto vt = new Voto(id, id_candidato, id_votante);

		VotoDAO vtdao = new VotoDAO();
		vtdao.addvoto(vt);
		sc.close();
	}*/


	
}
