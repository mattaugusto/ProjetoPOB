package modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

@Entity
@MappedSuperclass
public class Participante extends Pessoa
{
    private String instituicao;

    @ManyToMany(mappedBy = "participantes")
    List<Evento> eventos;

    public Participante(String nome, String cpf, String email, String instituicao)
    {
        super(nome, cpf, email);
        this.instituicao = instituicao;
    }

    public Participante()
    {
    }

    public String getInstituicao()
    {
        return this.instituicao;
    }

    public boolean taCadastroEvento(Evento evento)
    {
        return this.eventos.contains(evento);
    }

    @Override
    public String toString()
    {
        return "Participante [id="
                +this.getId()
                +", nome="+ this.getNome()
                +", instituicao=" + instituicao 
                +", email=" + this.getEmail()
                +", cpf=" + this.getCpf()
                + "]";
    }

    public void setInstituicao(String instituicao)
    {
        this.instituicao = instituicao;
    }
}
