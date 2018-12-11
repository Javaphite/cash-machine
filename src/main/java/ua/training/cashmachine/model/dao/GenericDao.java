package ua.training.cashmachine.model.dao;

import java.util.Collection;

public interface GenericDao<T> {

    T create(T entity);

    void delete(T entity);

    T update(T entity);

    T find(int id);

    Collection<T> findAll();
}
