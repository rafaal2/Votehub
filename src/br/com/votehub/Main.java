package br.com.votehub;

import br.com.votehub.model.vo.*;
import br.com.votehub.model.DAOs.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Main programa = new Main();
		programa.addCandidato();

	}
	
	private void addCandidato() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nome do candidato:");
		String nome = sc.next();
		System.out.println("ID do candidato:");
		int id = sc.nextInt();
		System.out.println("Cargo do candidato:");
		String cargo = sc.next();
	}

	/* private void addVoto() {
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
	}

	private void addVotante() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Digite o nome do votante: ");
		String nome = sc.nextLine();
		System.out.print("Digite o cpf do votante: ");
		String cpf = sc.nextLine();
		System.out.print("Digite a ocupação do votante: ");
		String ocupacao = sc.nextLine();

		Votante v = new Votante(cpf, nome, ocupacao);

		votanteDAO vdao = new votanteDAO();
		vdao.addVotante(v);
		sc.close();
	}
	*/
}
