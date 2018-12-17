package ua.training.cashmachine.model.dao.common;

import ua.training.cashmachine.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Base interface for mapping {@link ResultSet} rows into entities
 * @param <T> type of entity this mapper associated with.
 */
public interface Mapper<T> {

    /**
     * Base method for mapping one row of {@link ResultSet}
     * to entity instance of parameter type.
     * @param resultSet result set of SQL query to be processed.
     * @return entity instance or {@code null} if {@code resultSet} was empty.
     * @throws SQLException in case of problems during connection to DB.
     */
    T mapRow(ResultSet resultSet) throws SQLException;

    /**
     * Template method for mapping multiple rows from {@link ResultSet}
     * to {@link Collection} of entities.
     * @param resultSet result set of SQL query to be processed.
     * @return {@link Collection} of entity instances or empty one if {@code resultSet} was empty.
     * @throws SQLException in case of problems during connection to DB.
     */
    default Collection<T> mapAll(ResultSet resultSet) throws SQLException {
        Collection<T> entities = new LinkedList<>();
        while (!resultSet.isAfterLast()) {
            entities.add(mapRow(resultSet));
        }
        return entities;
    }
}
