package aplicacao;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Maranhão Ayres
 * Alunos: Matheus e Joel
 **********************************/


import fachada.Fachada;
import modelo.Palestra;
import modelo.Palestrante;
import modelo.Participante;
import modelo.Titulacao;
import modelo.Evento;

public class Teste {

	public Teste() {
		Fachada.inicializar();

		try {
//			Participante p;
//			Palestrante pa;
//			Titulacao t;
//			Palestra pl;
//			Evento e;
//			p=Fachada.cadastrarParticipante("João", "123123", "joao@gmail.com", "IFPB");
//			t=Fachada.cadastrarTitulacao("Doutorado");
//			pa=Fachada.cadastrarPalestrante("Carlos", "321321", "carlos@gmail.com", t);
//			pl=Fachada.cadastrarPalestra("Desenvolvimento Java", "Java", 120, pa);
//			e=Fachada.cadastrarEvento("EXPOTEC", "09/08/2017", "11/08/2017");
//			Fachada.adicionarParticipanteEvento("EXPOTEC", "123123");
			Fachada.adicionarPalestraEvento("EXPOTEC", "Desenvolvimento Java");
			System.out.println("cadastro concluido!");


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		
		
		
		System.out.println("\nfim do teste");
	}


	//  ***********************************************
	public static void main (String[] args)
	//  ***********************************************
	{
		Teste ap1 = new Teste();
	}

}
