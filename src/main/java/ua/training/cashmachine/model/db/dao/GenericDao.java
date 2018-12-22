package ua.training.cashmachine.model.db.dao;

import java.util.Collection;
import java.util.Optional;

public interface GenericDao<T> {

    T create(T entity);

    boolean update(T entity);

    boolean delete(T entity);

    Optional<T> find(int id);

    Collection<T> findAll();
}
