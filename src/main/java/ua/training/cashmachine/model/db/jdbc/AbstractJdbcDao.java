package ua.training.cashmachine.model.db.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.cashmachine.exception.UncheckedSQLException;
import ua.training.cashmachine.model.db.dao.GenericDao;
import ua.training.cashmachine.model.db.dao.Transaction;
import ua.training.cashmachine.model.db.mapper.GenericMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;

public abstract class AbstractJdbcDao<T> implements GenericDao<T> {

    static final Logger LOG = LoggerFactory.getLogger(AbstractJdbcDao.class);

    private Connection connection;
    private Transaction transaction;

    final GenericMapper<T> mapper;
    final Locale locale;

    QueryTemplate createQuery;
    QueryTemplate updateQuery;
    QueryTemplate deleteQuery;
    QueryTemplate findQuery;
    QueryTemplate findAllQuery;
    BiConsumer<T, Integer> idUpdater;

    AbstractJdbcDao(Connection connection, GenericMapper<T> mapper, Locale locale) {
        this.connection = connection;
        this.mapper = mapper;
        this.locale = locale;
    }

    AbstractJdbcDao(JdbcTransaction transaction, GenericMapper<T> mapper, Locale locale) {
        this.transaction = transaction;
        this.connection = transaction.getConnection();
        this.mapper = mapper;
        this.locale = locale;
    }

    @Override
    public T create(T entity) {
        try (PreparedStatement statement = statementOf(createQuery.get(locale))) {
            mapper.mapCreate(statement, entity).executeUpdate();
            try (ResultSet results = statement.getGeneratedKeys()) {
                if (results.next()) {
                    idUpdater.accept(entity, results.getInt(1));
                    commit();
                } else {
                    rollback();
                }
            }
        } catch (SQLException exception) {
            LOG.error("Entity creation failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
        return entity;
    }

    @Override
    public boolean update(T entity) {
        try (PreparedStatement statement = statementOf(updateQuery.get(locale))) {
            mapper.mapUpdate(statement, entity).executeUpdate();
            commit();
            return true;
        } catch (SQLException exception) {
            LOG.error("Entity update failed: ", exception);
            return false;
        }
    }

    @Override
    public boolean delete(T entity) {
        try (PreparedStatement statement = statementOf(deleteQuery.get(locale))) {
            mapper.mapDelete(statement, entity).executeUpdate();
            commit();
            return true;
        } catch (SQLException exception) {
            LOG.error("Entity delete failed: ", exception);
            return false;
        }
    }

    @Override
    public Optional<T> find(int id) {
        try (PreparedStatement statement = statementOf(findQuery.get(locale))) {
            statement.setInt(1, id);
            try (ResultSet results = statement.executeQuery()) {
                return Optional.ofNullable(mapper.mapFind(results));
            }
        } catch (SQLException exception) {
            LOG.error("Entity info reading failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }

    @Override
    public Collection<T> findAll() {
        try (PreparedStatement statement = statementOf(findAllQuery.get(locale));
             ResultSet results = statement.executeQuery()) {
                return mapper.mapFindAll(results);
        } catch (SQLException exception) {
            LOG.error("Entity info reading failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }

    public Optional<Transaction> getTransaction() {
        return Optional.of(transaction);
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    PreparedStatement statementOf(String query) {
        try {
            return Objects.requireNonNull(connection).prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException exception) {
            LOG.error("Statement creation failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }

    void commit() {
        try {
            if (null == transaction) {
                connection.commit();
            }
        } catch (SQLException exception) {
        LOG.error("Query commit failed: ", exception);
        throw new UncheckedSQLException(exception);
        }
    }

    void rollback() {
         try {
            if (null == transaction) {
                connection.rollback();
            }
        } catch (SQLException exception) {
            LOG.error("Query rollback failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }

    @Override
    public void close() {
        try {
            if(!connection.isClosed()) {
                connection.rollback();
                connection.close();
            }
        } catch (SQLException exception) {
            LOG.error("Dao connection closing failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }
}
