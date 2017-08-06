package modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Titulacao {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String titulo;
	@OneToMany(mappedBy = "tipoTitulacao") // Relacionamento Palestrante -> Titulação
	private List<Palestrante> palestrantes;
	public Titulacao(String titulo){
		this.titulo = titulo;
	}
	
	public Titulacao(){}
}
