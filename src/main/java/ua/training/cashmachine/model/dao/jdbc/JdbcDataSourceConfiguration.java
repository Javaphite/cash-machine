package ua.training.cashmachine.model.dao.jdbc;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.cashmachine.exception.UncheckedSQLException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

final class JdbcDataSourceConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(JdbcDataSourceConfiguration.class);

    private static volatile JdbcDataSourceConfiguration instance;

    private final DataSource dataSource;

    // Private constructor to prevent instantiation
    private JdbcDataSourceConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //Todo: read full config from property file
    //TODO: log me
    static JdbcDataSourceConfiguration getInstance() {
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


}
