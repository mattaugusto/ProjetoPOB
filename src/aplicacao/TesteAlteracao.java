package aplicacao;

import fachada.Fachada;

public class TesteAlteracao
{
	public static void main (String[] args)
	{
	    {
	        Fachada.inicializar();
	        try {
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        Fachada.finalizar();		
	        System.out.println("\nFim do teste altera��o");
	    }
	}
}