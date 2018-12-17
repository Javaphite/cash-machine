package ua.training.cashmachine.model.dao.mysql;

import ua.training.cashmachine.exception.UncheckedSQLException;
import ua.training.cashmachine.model.dao.common.BilingualDao;
import ua.training.cashmachine.model.dao.common.BilingualMapper;
import ua.training.cashmachine.model.dao.common.DataSourceConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public abstract class AbstractJdbcBilingualDao<T> extends AbstractJdbcBasicDao<T>
        implements BilingualDao<T>, BilingualMapper<T> {

    UnaryOperator<T> idUpdater;
    Supplier<String> createQuery;
    Supplier<String> updateQuery;

    AbstractJdbcBilingualDao(DataSourceConfiguration configuration) {
        super(configuration);
    }

    @Override
    public T create(T entity, Map<String, String> localizedValues) {
        T updatedEntity = entity;
        Connection connection = configuration.getConnection();

        try (PreparedStatement statement = configuration.getStatement(connection, createQuery.get())) {
            mapCreate(statement, updatedEntity, localizedValues).executeUpdate();

            try (ResultSet results = statement.getGeneratedKeys()) {
                if (results.next()) {
                    updatedEntity = idUpdater.apply(updatedEntity);
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
        return updatedEntity;
    }

    @Override
    public boolean update(T entity, Map<String, String> localizedValues) {
        Connection connection = configuration.getConnection();
        try (PreparedStatement statement = configuration.getStatement(connection, updateQuery.get())) {
            mapUpdate(statement, entity, localizedValues).executeUpdate();
            commitAndClose(connection);
            return true;
        } catch (SQLException exception) {
            LOG.error("Entity update failed: ", exception);
            rollbackAndClose(connection);
            return false;
        }
    }

}
