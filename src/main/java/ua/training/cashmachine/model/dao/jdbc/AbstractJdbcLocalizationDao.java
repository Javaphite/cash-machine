package ua.training.cashmachine.model.dao.jdbc;

import ua.training.cashmachine.exception.UncheckedSQLException;
import ua.training.cashmachine.model.dao.LocalizationDao;
import ua.training.cashmachine.model.dao.mapper.LocalizationMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

public abstract class AbstractJdbcLocalizationDao<T> extends AbstractJdbcDao<T> implements LocalizationDao<T> {

    QueryTemplate updateLocalizationQuery;

    AbstractJdbcLocalizationDao(Connection connection, LocalizationMapper<T> mapper, Locale locale) {
        super(connection, mapper, locale);
    }

    AbstractJdbcLocalizationDao(JdbcTransaction transaction, LocalizationMapper<T> mapper, Locale locale) {
        super(transaction, mapper, locale);
    }

    @Override
    public boolean updateLocalization(T entity, Map<Locale, Map<String, String>> localizationTable) {
        LocalizationMapper<T> localizationMapper = (LocalizationMapper<T>) mapper;
        try (PreparedStatement statement = statementOf(updateLocalizationQuery.get(locale))) {
            localizationMapper.mapLocalization(statement, entity, localizationTable).executeUpdate();
            commit();
            return true;
        } catch (SQLException exception) {
            LOG.error("Localization update failed: ", exception);
            rollback();
            throw new UncheckedSQLException(exception);
        }
    }
}
