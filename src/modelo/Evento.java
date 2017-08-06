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
	List<Participante> participantes;
	public Evento(){}
	
}
