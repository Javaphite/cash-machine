package ua.training.cashmachine.model.dao;

import java.util.Locale;
import java.util.Map;

public interface LocalizationDao<T> extends GenericDao<T> {

    void updateLocalization(T entity, Map<Locale, Map<String, String>> localizationTable);
}
