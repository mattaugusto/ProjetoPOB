package modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Evento
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String nome;

    @Temporal(TemporalType.DATE)
    private Date inicio = new Date();

    @Temporal(TemporalType.DATE)
    private Date fim = new Date();

    @ManyToMany
    List<Palestra> palestras;

    @ManyToMany
    List<Participante> participantes;

    public Evento(String nome, Date inicio, Date fim)
    {
        this.nome = nome;
        this.inicio = inicio;
        this.fim = fim;
    }

    public Evento()
    {
    }

    public void adicionarParticipante(Participante p)
    {
        this.participantes.add(p);
        p.eventos.add(this);
    }

    public void adicionarPalestra(Palestra p)
    {
        this.palestras.add(p);
        p.eventos.add(this);
    }

    public String getNome()
    {
        return this.nome;
    }

    @Override
    public String toString() {
        return "Evento [id=" + id + ", nome=" + nome + ", inicio=" + inicio + ", fim=" + fim + "]";
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public void setInicio(Date inicio)
    {
        this.inicio = inicio;
    }

    public void setFim(Date fim)
    {
        this.fim = fim;
    }

	public int getId() {
		return id;
	}

	public List<Palestra> getPalestras() {
		return palestras;
	}
	
	
}
