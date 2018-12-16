package ua.training.cashmachine.model.dao;

import java.util.Map;

public interface BilingualDao<T> extends BasicDao<T> {

    T create(T entity, Map<String, String> localizedValues);

    boolean update(T entity, Map<String, String> localizedValues);
}
