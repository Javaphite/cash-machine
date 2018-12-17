package ua.training.cashmachine.model.dao.common;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public interface BilingualMapper<T> extends BasicMapper<T> {
    //TODO: Doc me
    PreparedStatement mapCreate(PreparedStatement statement, T entity,
                                Map<String, String> localizedValues) throws SQLException;

    PreparedStatement mapUpdate(PreparedStatement statement, T entity,
                                Map<String, String> localizedValues) throws SQLException;;
}
