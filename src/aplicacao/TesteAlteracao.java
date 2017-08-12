package aplicacao;

import fachada.Fachada;

public class TesteAlteracao
{
    public static void main (String[] args)
    {
        Fachada.inicializar();
        try {
            Fachada.atualizarTitulacaoDoPalestrante("321651", "Mestrado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Fachada.finalizar();
        System.out.println("\nFim do teste alteração");
    }
}
