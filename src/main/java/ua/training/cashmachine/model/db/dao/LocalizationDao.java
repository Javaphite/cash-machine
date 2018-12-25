package ua.training.cashmachine.model.db.dao;

import java.util.Locale;
import java.util.Map;

public interface LocalizationDao<T> extends GenericDao<T> {

    boolean updateLocalization(T entity, Map<Locale, Map<String, String>> localizationTable);
}
