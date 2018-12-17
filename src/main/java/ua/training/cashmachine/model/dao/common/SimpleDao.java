package ua.training.cashmachine.model.dao.common;

public interface SimpleDao<T> extends BasicDao<T> {

    T create(T entity);

    boolean update(T entity);
}
