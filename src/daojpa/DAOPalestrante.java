package daojpa;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Palestrante;

public class DAOPalestrante extends DAO<Palestrante>
{
    public Palestrante localizarPeloCPF(String cpf)
    {
        try {
            Query q = manager.createQuery("select p from Palestrante p where p.cpf = :x");
            q.setParameter("x", cpf);
            return (Palestrante) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean isEmailDisponivel(String email)
    {
        try {
            Query q = manager.createQuery("select p from Palestrante p where p.email = :x");
            q.setParameter("x", email);
            q.getSingleResult();
            return false;
        } catch (NoResultException e) {
            return true;
        }
    }
}
