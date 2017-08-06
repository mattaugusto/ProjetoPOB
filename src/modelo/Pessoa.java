package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public abstract class Pessoa {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String cpf;
	private String email;
	
	
	public Pessoa(String nome, String cpf, String email){
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
	}
	public Pessoa() {}
	
}
