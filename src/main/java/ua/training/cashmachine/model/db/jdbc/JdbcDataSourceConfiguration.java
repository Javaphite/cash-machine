package ua.training.cashmachine.model.db.jdbc;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.cashmachine.exception.UncheckedSQLException;
import ua.training.cashmachine.model.db.DataSourceConfiguration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

final class JdbcDataSourceConfiguration implements DataSourceConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(JdbcDataSourceConfiguration.class);

    private static volatile DataSourceConfiguration instance;

    private final DataSource dataSource;

    // Private constructor to prevent instantiation
    private JdbcDataSourceConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //Todo: read full config from property file
    //TODO: log me
    static DataSourceConfiguration getInstance() {
        if (null == instance) {
            synchronized (JdbcDataSourceConfiguration.class) {
                if (null == instance) {
                    String url = "jdbc:mysql://localhost:3306/cashmachinedb";
                    MysqlConnectionPoolDataSource pooledDataSource = new MysqlConnectionPoolDataSource();
                    pooledDataSource.setUrl(url);
                    pooledDataSource.setUser("root");
                    pooledDataSource.setPassword("Grammar@109");
                    instance = new JdbcDataSourceConfiguration(pooledDataSource);
                }
            }
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        try {
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException exception) {
            LOG.error("Connection distribution failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }

    @Override
    public PreparedStatement getStatement(Connection connection, String query) {
        try {
            return Objects.requireNonNull(connection).prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException exception) {
            LOG.error("Statement creation failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }
}
