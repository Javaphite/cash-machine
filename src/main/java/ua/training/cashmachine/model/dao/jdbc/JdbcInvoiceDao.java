package ua.training.cashmachine.model.dao.jdbc;

import ua.training.cashmachine.exception.UncheckedSQLException;
import ua.training.cashmachine.model.dao.InvoiceDao;
import ua.training.cashmachine.model.dao.mapper.GenericMapper;
import ua.training.cashmachine.model.entity.Invoice;
import ua.training.cashmachine.model.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

import static ua.training.cashmachine.model.dao.jdbc.QueryTemplate.INVOICE_CREATE;
import static ua.training.cashmachine.model.dao.jdbc.QueryTemplate.INVOICE_DELETE;
import static ua.training.cashmachine.model.dao.jdbc.QueryTemplate.INVOICE_FIND_ALL;
import static ua.training.cashmachine.model.dao.jdbc.QueryTemplate.INVOICE_FIND_BY_ID;
import static ua.training.cashmachine.model.dao.jdbc.QueryTemplate.INVOICE_UPDATE;
import static ua.training.cashmachine.model.dao.jdbc.QueryTemplate.INVOICE_UPDATE_PRODUCTS;

public class JdbcInvoiceDao extends AbstractJdbcDao<Invoice> implements InvoiceDao {

    private QueryTemplate updateProductsInInvoiceQuery;

    JdbcInvoiceDao(Connection connection, GenericMapper<Invoice> mapper, Locale locale) {
        super(connection, mapper, locale);
        setupDaoConfiguration();
    }

    JdbcInvoiceDao(JdbcTransaction transaction, GenericMapper<Invoice> mapper, Locale locale) {
        super(transaction, mapper, locale);
        setupDaoConfiguration();
    }

    private void setupDaoConfiguration() {
        createQuery = INVOICE_CREATE;
        deleteQuery = INVOICE_DELETE;
        updateQuery = INVOICE_UPDATE;
        findQuery = INVOICE_FIND_BY_ID;
        findAllQuery = INVOICE_FIND_ALL;
        updateProductsInInvoiceQuery = INVOICE_UPDATE_PRODUCTS;
        idUpdater = Invoice::setInvoiceId;
    }

    @Override
    public void updateProductsInInvoice(Invoice invoice) {
        Map<Product, Long> products = invoice.getProducts();
        try (PreparedStatement statement = statementOf(updateProductsInInvoiceQuery.get(locale))) {
            for (Map.Entry<Product, Long> position: products.entrySet()) {
                statement.setInt(1, invoice.getInvoiceId());
                statement.setInt(2, position.getKey().getProductId());
                statement.setLong(3, position.getValue());
                statement.addBatch();
            }
            statement.executeUpdate();
            commit();
        } catch (SQLException exception) {
            LOG.error("Entity update failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }
}
