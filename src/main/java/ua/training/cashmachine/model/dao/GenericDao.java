package ua.training.cashmachine.model.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public interface GenericDao<T> {

    Logger LOG = LoggerFactory.getLogger(GenericDao.class);

    T create(T entity);

    void delete(T entity);

    T update(T entity);

    T find(int id);

    Collection<T> findAll();
}
