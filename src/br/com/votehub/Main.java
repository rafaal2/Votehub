package br.com.votehub;

import br.com.votehub.model.vo.*;
import br.com.votehub.model.DAOs.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Main programa = new Main();
		programa.addvoto();
		
	}private void addvoto() {
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
	
	
		   //private void addvotante() {
			    //Scanner sc = new Scanner(System.in);
		        //System.out.print("Digite o id do votante: ");
		        //int id = sc.nextInt();
		        //System.out.print("Digite o nome do votante: ");
		        //String nome = sc.next();
		        //System.out.print("Digite o cpf do votante: ");
		       //String cpf = sc.next();
		       // System.out.print("Digite a ocupação do votante: ");
		       // String ocupação = sc.next();

		        //Votante v = new Votante(id, nome, cpf, ocupação);

		        //votanteDAO vdao = new votanteDAO();
		       // vdao.addvotante(v);
		        //sc.close();
		   }
	
	
