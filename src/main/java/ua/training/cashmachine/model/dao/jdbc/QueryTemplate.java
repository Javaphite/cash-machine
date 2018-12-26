package ua.training.cashmachine.model.dao.jdbc;

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
    TURN_UPDATE("turn.update"),
    INVOICE_FIND_ALL("invoice.find.all"),
    INVOICE_FIND_BY_ID("invoice.find.id"),
    INVOICE_CREATE("invoice.create"),
    INVOICE_DELETE("invoice.delete"),
    INVOICE_UPDATE("invoice.update"),
    INVOICE_UPDATE_PRODUCTS("invoice.m2m.create"),
    PRODUCT_FIND_ALL("product.find.all"),
    PRODUCT_FIND_BY_ID("product.find.id"),
    PRODUCT_CREATE("product.create"),
    PRODUCT_DELETE("product.delete"),
    PRODUCT_UPDATE("product.update"),
    PRODUCT_LOCALIZATION_UPDATE("product.localization"),
    SUPPLY_FIND_ALL("supply.find.all"),
    SUPPLY_FIND_BY_ID("supply.find.id"),
    SUPPLY_CREATE("supply.create"),
    SUPPLY_DELETE("supply.delete"),
    SUPPLY_UPDATE("supply.update");

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
