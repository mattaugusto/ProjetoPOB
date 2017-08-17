package aplicacao;

import fachada.Fachada;

public class TesteConsulta
{
    public static void main (String[] args)
    {
        Fachada.inicializar();
        try {
//            System.out.println(Fachada.consulta1());
//            System.out.println(Fachada.consulta2());
//            System.out.println(Fachada.consulta3());
//            System.out.println(Fachada.consulta4("Mestrado"));
        	System.out.println(Fachada.consulta5());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Fachada.finalizar();		
        System.out.println("\nFim do teste consulta");
    }
}
