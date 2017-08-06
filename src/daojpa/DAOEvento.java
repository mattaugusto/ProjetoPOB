package daojpa;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Evento;
import modelo.Titulacao;

public class DAOEvento extends DAO<Evento>{
	public Evento localizarPeloNome(String nome){
		try{
			Query q = manager.createQuery("select e from Evento e where e.nome= '" + nome +"'");
			return (Evento) q.getSingleResult();
			
		}catch(NoResultException e){			
			return null;
		}
		
	}
}
