package daojpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Evento;

public class DAOEvento extends DAO<Evento>
{
    public Evento localizarPeloNome(String nome)
    {
        try {
            Query q = manager.createQuery("select e from Evento e where e.nome = :x");
            q.setParameter("x", nome);
            return (Evento) q.getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }
    
    @SuppressWarnings("unchecked")
	public List<Object[]>  totalParticipantesEvento()
    {
        Query q = manager.createNativeQuery("select NOME, (SELECT count(1) " +
        		"FROM EVENTO_PARTICIPANTE EP WHERE EP.EVENTOS_ID = E.ID) total " +
        		"FROM EVENTO E");
        return q.getResultList();
    }
}
