package daojpa;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Participante;

public class DAOParticipante extends DAO<Participante>
{
    public Participante localizarPeloCPF(String cpf)
    {
        try {
            Query q = manager.createQuery("select p from Participante p where p.cpf= :x");
            q.setParameter("x", cpf);
            return (Participante) q.getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }

    public Participante localizar(Object id)
    {
        return manager.find(Participante.class, id);
    }
}
