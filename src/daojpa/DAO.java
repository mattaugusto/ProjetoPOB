/**IFPB - Curso SI 
 * Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */
package daojpa;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DAO<T> implements DAOInterface<T>
{
    protected static EntityManager manager;

    public DAO()
    {
    }

    public static void abrir()
    {
        if(manager==null){
            EntityManagerFactory factory =
                    Persistence.createEntityManagerFactory("evento");
            manager = factory.createEntityManager();
        }
    }

    public static void fechar()
    {
        if(manager != null) {
            manager = null;
        }
    }

    public void persistir(T obj)
    {
        manager.persist(obj);
    }

    public T atualizar(T obj)
    {
        return manager.merge(obj);
    }

    public void apagar(T obj)
    {
        manager.remove(obj);
    }

    public void reler(T obj)
    {
        manager.refresh(obj);
    }

    @SuppressWarnings("unchecked")
    public List<T> listar()
    {
        Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        Query query = manager.createQuery("select x from " + type.getSimpleName() + " x");
        return (List<T>) query.getResultList();
    }

    public static void iniciar(){
        if (!manager.getTransaction().isActive()) {
            manager.getTransaction().begin();
        }
    }

    public static void efetivar(){
        if (manager.getTransaction().isActive()) {
            manager.getTransaction().commit();
            manager.clear();
        }
    }

    public static void cancelar()
    {
        if (manager.getTransaction().isActive()) {
            manager.getTransaction().rollback();
        }
    }
}