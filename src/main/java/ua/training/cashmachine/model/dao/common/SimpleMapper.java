package ua.training.cashmachine.model.dao.common;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SimpleMapper<T> extends BasicMapper<T> {

    //TODO: Doc me
    PreparedStatement mapCreate(PreparedStatement statement, T entity) throws SQLException;

    PreparedStatement mapUpdate(PreparedStatement statement, T entity) throws SQLException;
}
