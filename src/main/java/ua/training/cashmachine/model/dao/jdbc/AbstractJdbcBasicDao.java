package ua.training.cashmachine.model.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.cashmachine.exception.UncheckedSQLException;
import ua.training.cashmachine.model.dao.common.BasicDao;
import ua.training.cashmachine.model.dao.common.DataSourceConfiguration;
import ua.training.cashmachine.model.dao.common.BasicMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

public abstract class AbstractJdbcBasicDao<T> implements BasicDao<T>, BasicMapper<T> {

    static final Logger LOG = LoggerFactory.getLogger(AbstractJdbcBasicDao.class);

    DataSourceConfiguration configuration;
    Supplier<String> deleteQuery;
    Supplier<String> findQuery;
    Supplier<String> findAllQuery;

    AbstractJdbcBasicDao(DataSourceConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public boolean delete(T entity) {
        Connection connection = configuration.getConnection();
        try (PreparedStatement statement = configuration.getStatement(connection, deleteQuery.get())) {
            mapDelete(statement, entity).executeUpdate();
            commitAndClose(connection);
            return true;
        } catch (SQLException exception) {
            LOG.error("Entity delete failed: ", exception);
            rollbackAndClose(connection);
            return false;
        }
    }

    @Override
    public Optional<T> find(int id) {
        try (Connection connection = configuration.getConnection();
             PreparedStatement statement = configuration.getStatement(connection, findQuery.get())) {
            statement.setInt(1, id);
            try (ResultSet results = statement.executeQuery()) {
                return Optional.ofNullable(mapFind(results));
            }
        } catch (SQLException exception) {
            LOG.error("Entity info reading failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }

    @Override
    public Collection<T> findAll() {
        try (Connection connection = configuration.getConnection();
             PreparedStatement statement = configuration.getStatement(connection, findAllQuery.get())) {
            try (ResultSet results = statement.executeQuery()) {
                return findAllMap(results);
            }
        } catch (SQLException exception) {
            LOG.error("Entity info reading failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }
}
