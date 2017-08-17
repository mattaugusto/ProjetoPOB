package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Pessoa
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String nome;

    private String cpf;

    private String email;

    public Pessoa(String nome, String cpf, String email)
    {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public Pessoa()
    {
    }

    public String getNome()
    {
        return this.nome;
    }

    public String getCpf()
    {
        return this.cpf;
    }

    public String getEmail()
    {
        return this.email;
    }

    public int getId()
    {
        return this.id;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
