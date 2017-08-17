package daojpa;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Titulacao;

public class DAOTitulacao extends DAO<Titulacao>
{
    public Titulacao localizarPeloTitulo(String titulo)
    {
        try {
            Query q = manager.createQuery("select t from Titulacao t where t.titulo = :x");
            q.setParameter("x", titulo);
            return (Titulacao) q.getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }

    public Titulacao localizar(Object id)
    {
        return manager.find(Titulacao.class, id);
    }
}