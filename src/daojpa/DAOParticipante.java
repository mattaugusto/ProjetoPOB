package daojpa;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Participante;

public class DAOParticipante extends DAO<Participante>
{
    public Participante localizarPeloCPF(String cpf)
    {
        try {
            Query q = manager.createQuery("select p from Participante p where p.cpf= '" + cpf +"'");
            return (Participante) q.getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }
}
