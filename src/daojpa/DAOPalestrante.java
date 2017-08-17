package daojpa;

import java.util.List;

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

    @SuppressWarnings("unchecked")
	public List<Palestrante> consultarPalestrantesSemPalestras()
    {
        Query q = manager.createQuery("select p from Palestrante p where p.palestras is empty");
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
	public List<Palestrante> consultarPalestrantesSemTitulo()
    {
        Query q = manager.createQuery("select p from Palestrante p where p.tipoTitulacao is NULL");
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
	public List<Palestrante> consultarPalestrantesPorTitulo(String titulo)
    {
        Query q = manager.createQuery("select p from Palestrante p where p.tipoTitulacao.titulo = :x");
        q.setParameter("x", titulo);
        return q.getResultList();
    }

    public Palestrante localizar(Object id)
    {
        return manager.find(Palestrante.class, id);
    }
}
