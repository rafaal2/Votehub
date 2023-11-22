package br.com.votehub;

import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import br.com.votehub.model.DAOs.CandidatoDAO;
import br.com.votehub.model.DAOs.VotanteDAO;
import br.com.votehub.model.DAOs.VotoDAO;
import br.com.votehub.model.criptografia.Encriptador;
import br.com.votehub.model.vo.Candidato;
import br.com.votehub.model.vo.Votante;
import br.com.votehub.model.vo.Voto;
import br.com.votehub.view.*;

public class Main {

	public static void main(String[] args) throws SQLException {
		SwingUtilities.invokeLater(ConfirmacaoVoto::new);
		Scanner sc = new Scanner(System.in);
		System.out.println("digite sua senha");
		String senhadigit = sc.next();
		boolean check = Encriptador.verificarsenhavot(senhadigit);
		if (check) {
			Main programa = new Main();
			programa.operação();
		}
	}
  // ____TELAS____
	// SwingUtilities.invokeLater(TelaVotacao::new);
	// SwingUtilities.invokeLater(ConfirmacaoVoto::new);   
	// SwingUtilities.invokeLater(LoginUsuario::new);
	
	public void operação() {
		Scanner sc = new Scanner(System.in);
		System.out.println("operação 1: adicionar votante");
		System.out.println("operação 2: adicionar candidato");
		System.out.println("operação 3: adicionar voto");
		System.out.println("operação 4: mostrar votantes");
		System.out.print("digite a operação a ser realizada: ");
		int op = sc.nextInt();
		if (op == 1) {
			addVotante();
		} else if (op == 2) {
			addCandidato();
		} else if (op == 3) {
			addVoto();
		} else if (op == 4) {
			mostrarVotantes();
		} else {
			System.out.println("digite uma operação valida");
		}
		sc.close();

	}

	private void addVotante() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Digite o id do votante: ");
		int id_votante = sc.nextInt();
		System.out.print("Digite a matricula do votante: ");
		String matricula = sc.next();
		System.out.print("Digite o nome do votante: ");
		String nome = sc.next();
		System.out.print("Digite a senha do votante: ");
		String senha = sc.next();
		System.out.print("Digite a ocupação do votante: ");
		String ocupação = sc.next();

		Votante v = new Votante(id_votante, matricula, nome, senha, ocupação);

		VotanteDAO vdao = new VotanteDAO();
		vdao.addVotante(v);
		sc.close();
	}

	private void addCandidato() {

		Scanner sc = new Scanner(System.in);
		System.out.println("digite o Numero do candidato:");
		String numero_candidato = sc.next();
		System.out.println("digite o nome do candidato:");
		String nome = sc.next();
		System.out.println("digite o Cargo do candidato:");
		String cargo = sc.next();

		Candidato c = new Candidato(numero_candidato, nome, cargo);
		CandidatoDAO cdao = new CandidatoDAO();
		cdao.addCandidato(c);
		sc.close();

	}

	private void addVoto() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Digite o id do voto: ");
		int id_voto = sc.nextInt();
		System.out.print("Digite o id do candidato a ser votado: ");
		String numero_candidato = sc.next();
		System.out.print("Digite o id do vontate que vai votar: ");
		int id_votante = sc.nextInt();

		Voto vt = new Voto(id_voto, numero_candidato, id_votante);

		VotoDAO vtdao = new VotoDAO();
		vtdao.addVoto(vt);
		sc.close();
	}

	private void mostrarVotantes() {
		VotanteDAO cd = new VotanteDAO();
		cd.mostrarVotantes();

	}

}
