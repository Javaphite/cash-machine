package ua.training.cashmachine.model.dao.mapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

public interface LocalizationMapper<T> extends GenericMapper<T> {

    PreparedStatement mapLocalization(PreparedStatement statement, T entity,
                                      Map<Locale, Map<String, String>> localizationTable) throws SQLException;
}
