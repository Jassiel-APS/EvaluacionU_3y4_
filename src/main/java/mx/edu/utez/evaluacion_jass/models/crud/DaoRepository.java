package mx.edu.utez.evaluacion_jass.models.crud;

import java.util.List;

public interface DaoRepository<T> {
    List<T> findAll();
    T findOne(Long id);
    boolean save(T object);
    boolean delete(Long id);
}
