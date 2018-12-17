package ua.training.cashmachine.model.dao.common;

import java.sql.Connection;
import java.sql.PreparedStatement;

public interface DataSourceConfiguration {

    Connection getConnection();

    PreparedStatement getStatement(Connection connection, String query);
}
