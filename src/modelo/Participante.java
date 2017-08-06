package modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Participante extends Pessoa {
	private String instituicao;
	
//	@ManyToMany(mappedBy = "participantes")
//	List<Evento> eventos;
	
	public Participante(String nome, String cpf, String email, String instituicao){
		super(nome, cpf, email);
		this.instituicao = instituicao;
	}
	public Participante(){}
}
