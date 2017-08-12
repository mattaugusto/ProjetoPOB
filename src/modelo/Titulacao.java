package modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

@Entity
public class Titulacao
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String titulo;

    @OneToMany(mappedBy = "tipoTitulacao")
    private List<Palestrante> palestrantes;

    public Titulacao(String titulo)
    {
        this.titulo = titulo;
    }

    public Titulacao()
    {
    }

    public String getTitulo()
    {
        return this.titulo;
    }

    // http://docs.oracle.com/javaee/7/api/javax/persistence/PreRemove.html
    @PreRemove
    private void removerTitualacaoDosPalestrantes()
    {
        for (Palestrante p : palestrantes) {
            p.setTitulacao(null);
        }
    }
}
