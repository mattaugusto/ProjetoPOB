package modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Palestrante extends Pessoa{
	@ManyToOne // Relacionamento Palestrante -> Titulação
	private Titulacao tipoTitulacao;
	
	public Palestrante(String nome, String cpf, String email, Titulacao tipoTitulacao){
		super(nome, cpf, email);
		this.tipoTitulacao = tipoTitulacao;
	}
	public Palestrante(){}
	@OneToMany(mappedBy = "palestrante") // Relacionamento Palestrante -> Palestras
	private List<Palestra> palestras;

}
