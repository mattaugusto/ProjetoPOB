package modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Palestra
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String titulo;

	private String descricao;

	private String duracao;

	@ManyToOne
	private Palestrante palestrante;

	@ManyToMany(mappedBy = "palestras")
	List<Evento> eventos;

	public Palestra(String titulo, String descricao, String duracao, Palestrante palestrante)
	{
		this.titulo = titulo;
		this.descricao = descricao;
		this.duracao = duracao;
		this.palestrante= palestrante;
	}

	public Palestra()
	{
	}
	
	public String getTitulo()
	{
		return this.titulo;
	}
	
	public String getDescricao()
	{
		return this.descricao;
	}
	
	public String getDuracao()
	{
		return this.duracao;
	}
}
