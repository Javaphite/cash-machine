package ua.training.cashmachine.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public interface Mapper<T> {

    T mapRow(ResultSet resultSet) throws SQLException;

    Collection<T> mapAll(ResultSet resultSet) throws SQLException;
}
