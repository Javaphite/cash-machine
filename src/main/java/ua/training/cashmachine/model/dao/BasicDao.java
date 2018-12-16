package ua.training.cashmachine.model.dao;

import java.util.Collection;
import java.util.Optional;

public interface BasicDao<T> extends TransactionAware {

    boolean delete(T entity);

    Optional<T> find(int id);

    Collection<T> findAll();
}
