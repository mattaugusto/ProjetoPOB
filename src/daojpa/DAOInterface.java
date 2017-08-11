package daojpa;

import java.util.List;

public interface DAOInterface<T>
{
    public void persistir(T obj);
    public T atualizar(T obj);
    public void apagar(T obj) ;
    public List<T> listar();
    public void reler(T obj);
}
