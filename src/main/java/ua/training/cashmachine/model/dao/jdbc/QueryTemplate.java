package ua.training.cashmachine.model.dao.jdbc;

import java.util.Locale;
import java.util.ResourceBundle;

public enum QueryTemplate {
    USER_FIND_BY_CREDENTIALS("user.find.credentials"),
    USER_FIND_ALL("user.find.all"),
    USER_FIND_BY_ID("user.find.id"),
    USER_CREATE("user.create"),
    USER_DELETE("user.delete"),
    USER_UPDATE("user.update"),
    TURN_FIND_ALL("turn.find.all"),
    TURN_FIND_BY_ID("turn.find.id"),
    TURN_CREATE("turn.create"),
    TURN_DELETE("turn.delete"),
    TURN_UPDATE("turn.update");

    private static final String QUERIES_BUNDLE = "sql/statements";

    private final String bundleKey;

    QueryTemplate(String bundleKey) {
        this.bundleKey = bundleKey;
    }

    public String getBundleKey() {
        return bundleKey;
    }

    public String getQuery(Locale locale) {
        return ResourceBundle.getBundle(QUERIES_BUNDLE, locale).getString(bundleKey);
    }

    public String getQuery() {
        return getQuery(Locale.getDefault());
    }
}
