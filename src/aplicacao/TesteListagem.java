package aplicacao;

import fachada.Fachada;

public class TesteListagem
{
    public static void main (String[] args)
    {
        Fachada.inicializar();
        try {
            System.out.println(Fachada.listarEventos());
            System.out.println(Fachada.listarPalestras());
            System.out.println(Fachada.listarParticipantes());
            System.out.println(Fachada.listarPalestrantes());
            System.out.println(Fachada.listarTitulacoes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Fachada.finalizar();
        System.out.println("\nFim do teste de listagem");
    }
}
