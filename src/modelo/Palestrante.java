package modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Palestrante extends Pessoa
{
    @ManyToOne
    private Titulacao tipoTitulacao;

    @OneToMany(mappedBy = "palestrante")
    private List<Palestra> palestras;

    public Palestrante(String nome, String cpf, String email, Titulacao tipoTitulacao)
    {
        super(nome, cpf, email);
        this.tipoTitulacao = tipoTitulacao;
    }

    public Palestrante()
    {
    }

    public void setTitulacao(Titulacao t)
    {
        this.tipoTitulacao = t;
    }

    @Override
    public String toString()
    {
        return "Palestrante [nome="+ this.getNome()
                +", tipoTitulacao=" + tipoTitulacao
                +", email=" + this.getEmail()
                +", cpf=" + this.getCpf()
                + "]";
    }
}
