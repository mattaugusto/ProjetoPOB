package aplicacao;

import fachada.Fachada;

public class TesteListagem
{
    public static void main (String[] args)
    {
        Fachada.inicializar();
        try {
            System.out.println(Fachada.listarEventos());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Fachada.finalizar();
        System.out.println("\nFim do teste listagem");
    }
}
