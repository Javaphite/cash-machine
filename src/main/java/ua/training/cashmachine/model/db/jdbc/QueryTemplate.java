package ua.training.cashmachine.model.db.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.ResourceBundle;

public enum QueryTemplate {
    USER_FIND_BY_CREDENTIALS("user.find.credentials"),
    USER_FIND_ALL("user.find.all"),
    USER_FIND_BY_ID("user.find.id"),
    USER_CREATE("user.create"),
    USER_DELETE("user.delete"),
    USER_UPDATE("user.update"),
    USER_LOCALIZATION_UPDATE("user.localization"),
    TURN_FIND_ALL("turn.find.all"),
    TURN_FIND_BY_ID("turn.find.id"),
    TURN_CREATE("turn.create"),
    TURN_DELETE("turn.delete"),
    TURN_UPDATE("turn.update");

    private static final Logger LOG = LoggerFactory.getLogger(QueryTemplate.class);
    private static final String QUERIES_BUNDLE = "queries";

    private final String bundleKey;

    QueryTemplate(String bundleKey) {
        this.bundleKey = bundleKey;
    }

    public String get(Locale locale) {
        LOG.debug("Retrieving query with key: {}, locale: {}", bundleKey, locale);
        return ResourceBundle.getBundle(QUERIES_BUNDLE, locale).getString(bundleKey);
    }
}
