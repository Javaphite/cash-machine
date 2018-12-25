package ua.training.cashmachine.model.dao;

import java.util.Collection;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {

    T create(T entity);

    void update(T entity);

    void delete(T entity);

    Optional<T> find(int id);

    Collection<T> findAll();
}
