package ua.training.cashmachine.model.dao.jdbc;

import ua.training.cashmachine.exception.UncheckedSQLException;
import ua.training.cashmachine.model.dao.common.DataSourceConfiguration;
import ua.training.cashmachine.model.dao.common.SimpleDao;
import ua.training.cashmachine.model.dao.common.SimpleMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public abstract class AbstractJdbcSimpleDao<T> extends AbstractJdbcBasicDao<T>
        implements SimpleDao<T>, SimpleMapper<T> {

    BiConsumer<T, Integer> idUpdater;
    Supplier<String> createQuery;
    Supplier<String> updateQuery;

    AbstractJdbcSimpleDao(DataSourceConfiguration configuration) {
        super(configuration);
    }

    @Override
    public T create(T entity) {
        Connection connection = configuration.getConnection();

        try (PreparedStatement statement = configuration.getStatement(connection, createQuery.get())) {
            mapCreate(statement, entity).executeUpdate();

            try (ResultSet results = statement.getGeneratedKeys()) {
                if (results.next()) {
                    Integer entityId = results.getInt(1);
                    idUpdater.accept(entity, entityId);
                    commitAndClose(connection);
                } else {
                    rollbackAndClose(connection);
                }
            }

        } catch (SQLException exception) {
            LOG.error("Entity creation failed: ", exception);
            rollbackAndClose(connection);
            throw new UncheckedSQLException(exception);
        }
        return entity;
    }

    @Override
    public boolean update(T entity) {
        Connection connection = configuration.getConnection();
        try (PreparedStatement statement = configuration.getStatement(connection, updateQuery.get())) {
            mapUpdate(statement, entity).executeUpdate();
            commitAndClose(connection);
            return true;
        } catch (SQLException exception) {
            LOG.error("Entity update failed: ", exception);
            rollbackAndClose(connection);
            return false;
        }
    }
}
