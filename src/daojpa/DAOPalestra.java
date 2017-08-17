package daojpa;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Palestra;

public class DAOPalestra extends DAO<Palestra>
{
    public Palestra localizarPeloTitulo(String titulo)
    {
        try {
            Query q = manager.createQuery("select p from Palestra p where p.titulo= :x");
            q.setParameter("x", titulo);
            return (Palestra) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Palestra localizar(Object id)
    {
        return manager.find(Palestra.class, id);
    }
}
