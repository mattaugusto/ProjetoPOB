package aplicacao;

import fachada.Fachada;

public class TesteExclusao
{
    public static void main (String[] args)
    {
        Fachada.inicializar();
        try {
//        	  Fachada.apagarParticipante("321654");
//            Fachada.apagarEvento("EXPOTEC");
//            Fachada.apagarPalestra("Desenvolvimento PHP");
//            Fachada.apagarTitulacao("Doutorado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Fachada.finalizar();
        System.out.println("\nFim do teste exclusão");
    }
}
