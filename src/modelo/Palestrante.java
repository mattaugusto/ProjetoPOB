package modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@Entity
@MappedSuperclass
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
        return "Palestrante [id=" + this.getId()
                + ", nome="+ this.getNome()
                +", tipoTitulacao=" + (tipoTitulacao !=null ? tipoTitulacao.getTitulo() :"Sem Titulação")
                +", email=" + this.getEmail()
                +", cpf=" + this.getCpf()
                + "]";
    }
}
