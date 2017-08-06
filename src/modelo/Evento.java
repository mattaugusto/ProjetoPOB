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
public class Evento {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	@Temporal(TemporalType.DATE)
	private Date inicio = new Date();
	@Temporal(TemporalType.DATE)
	private Date fim = new Date();
	
	@ManyToMany
	List<Palestra> palestras; // Relacionamento Palestra -> Evento
	
	@ManyToMany
	List<Participante> participantes; // Relacionamento Participante -> Evento
	public Evento(String nome, Date inicio, Date fim){
		this.nome = nome;
		this.inicio = inicio;
		this.fim = fim;
	}
	public Evento(){}
	
	public void adicionarParticipante(Participante p){
		this.participantes.add(p);
		p.eventos.add(this);
	}
	
	public void adicionarPalestra(Palestra p){
		this.palestras.add(p);
		p.eventos.add(this);
	}
}
