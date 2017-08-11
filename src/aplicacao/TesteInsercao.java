/**
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Prof. Fausto Maranhão Ayres
 * Persistencia de Objetos
 * Alunos: Joel e Matheus
 *
 */
package aplicacao;

import fachada.Fachada;


public class TesteInsercao
{
    public static void associarPalestraEvento() throws Exception
    {
    	Fachada.adicionarPalestraEvento("EXPOTEC", "Desenvolvimento PHP");
    }
    
    public static void associarParticipanteEvento() throws Exception
    {
    	Fachada.adicionarParticipanteEvento("EXPOTEC", "321654");
    }
	
    public static void cadastrarPalestras() throws Exception
    {
		String[][] palestrasDados = new String[][] {
			new String[] {"Desenvolvimento PHP", "PHP", "00:00:00", "321651"},
		};

		for (String[] palestraDados : palestrasDados) {
			Fachada.cadastrarPalestra(
					palestraDados[0],
					palestraDados[1],
					palestraDados[2],
					palestraDados[3]
			);
		}
    }
	
	public static void cadastrarPalestrantes() throws Exception
	{
		String[][] palestrantesDados = new String[][] {
			new String[] {"João", "123123", "joao@gmail.com", "Mestrado"},
			new String[] {"Joel", "321654", "joel@gmail.com", "Doutorado"},
			new String[] {"Mônica", "321651", "monica@gmail.com", "Doutorado"},
		};

		for (String[] palestranteDados : palestrantesDados) {
			Fachada.cadastrarPalestrante(
					palestranteDados[0],
					palestranteDados[1],
					palestranteDados[2],
					palestranteDados[3]
			);
		}
	}
	
	public static void cadastrarEventos() throws Exception
	{
		String[][] eventosDados = new String[][] {
			new String[] {"EXPOTEC", "09/08/2017", "11/08/2017"},
			new String[] {"SIMPIF", "20/11/2017", "23/11/2017"},
		};
		for (String[] eventoDados : eventosDados) {			
			Fachada.cadastrarEvento(
					eventoDados[0],
					eventoDados[1],
					eventoDados[2]
			);
		}
	}
	
	public static void cadastrarTitulacoes() throws Exception
	{
		String[] titualcoes = new String[] {"Graduação", "Mestrado", "Doutorado", "Outra"};
		
		for (String tituacao : titualcoes) {
			Fachada.cadastrarTitulacao(tituacao);
		}
	}
	
	public static void cadastrarParticipantes() throws Exception
	{
		String[][] participantesDados = new String[][] {
			new String[] {"João", "123123", "joao@gmail.com", "IFPB"},
			new String[] {"Joel", "321654", "joel@gmail.com", "IFPB"},
		};
		
		for (String[] participanteDados : participantesDados) {
			Fachada.cadastrarParticipante(
					participanteDados[0],
					participanteDados[1],
					participanteDados[2],
					participanteDados[3]
			);
		}
	}

	public static void main (String[] args)
	{
	    {
	        Fachada.inicializar();
	        try {
				cadastrarParticipantes();
				cadastrarTitulacoes();
				cadastrarEventos();
				cadastrarPalestrantes();
				cadastrarPalestras();
				associarParticipanteEvento();
	        	associarPalestraEvento();
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        Fachada.finalizar();		
	        System.out.println("\nFim do teste insercao");
	    }
	}
}
