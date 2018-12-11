package ua.training.cashmachine.model.dao.jdbc;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import ua.training.cashmachine.exception.UncheckedSQLException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

final class JdbcConnectionPoolHolder {

    private static volatile DataSource dataSource;

    private JdbcConnectionPoolHolder() {}

    //Todo: read full config from property file
    //TODO: log me
    private static DataSource getDataSource() {
        if (null == dataSource) {
            synchronized (JdbcConnectionPoolHolder.class) {
                if (dataSource == null) {
                    String url = "jdbc:mysql://localhost:3306/cashmachinedb";
                    MysqlConnectionPoolDataSource pooledDataSource = new MysqlConnectionPoolDataSource();
                    pooledDataSource.setUrl(url);
                    pooledDataSource.setUser("root");
                    pooledDataSource.setPassword("----");
                    dataSource = pooledDataSource;
                }
            }
        }
        return dataSource;
    }

    static Connection getConnection() {
        try {
            Connection connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException exception) {
            //TODO: log me
            //LOG.error("Connection pooling exception: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }

    static PreparedStatement getStatement(Connection connection, String sql) {
        try {
            return Objects.requireNonNull(connection).prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException exception) {
            //TODO: log me
            //LOG.error("Statement creation exception: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }
}
