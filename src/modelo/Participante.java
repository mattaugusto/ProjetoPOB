package modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id")
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
}
