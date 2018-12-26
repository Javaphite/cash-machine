package ua.training.cashmachine.model.dao.mapper;

import ua.training.cashmachine.model.dao.TurnDao;
import ua.training.cashmachine.model.dao.UserDao;
import ua.training.cashmachine.model.entity.Invoice;
import ua.training.cashmachine.model.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

public class InvoiceMapper implements GenericMapper<Invoice> {

    private UserDao userDao;
    private TurnDao turnDao;

    InvoiceMapper(UserDao userDao, TurnDao turnDao) {
        this.userDao = userDao;
        this.turnDao = turnDao;
    }

    @Override
    public PreparedStatement mapCreate(PreparedStatement statement, Invoice entity) throws SQLException {
        statement.setInt(1, entity.getUser().getUserId());
        statement.setLong(2, entity.getTotalCost());
        statement.setTimestamp(3, Timestamp.valueOf(entity.getCreationTime()));
        statement.setInt(4, entity.getTurn().getTurnId());
        statement.setInt(5, entity.isRefund()?1:0);
        statement.setInt(6, entity.isArchived()?1:0);
        return statement;
    }

    @Override
    public PreparedStatement mapUpdate(PreparedStatement statement, Invoice entity) throws SQLException {
        statement.setInt(1, entity.getUser().getUserId());
        statement.setLong(2, entity.getTotalCost());
        statement.setTimestamp(3, Timestamp.valueOf(entity.getCreationTime()));
        statement.setInt(4, entity.getTurn().getTurnId());
        statement.setInt(5, entity.isRefund()?1:0);
        statement.setInt(6, entity.isArchived()?1:0);
        statement.setInt(7, entity.getInvoiceId());
        return statement;
    }

    @Override
    public PreparedStatement mapDelete(PreparedStatement statement, Invoice entity) throws SQLException {
        statement.setInt(1, entity.getInvoiceId());
        return statement;
    }

    @Override
    public Invoice mapFind(ResultSet resultSet) throws SQLException {
        Invoice invoice = null;
        Map<Product, Long> products = invoice.getProducts();
        if (resultSet.next()) {
            invoice = new Invoice();
            invoice.setInvoiceId(resultSet.getInt(1));
            invoice.setUser(userDao.find(resultSet.getInt(2)).orElse(null));
            invoice.setTotalCost(resultSet.getLong(3));
            invoice.setCreationTime(resultSet.getTimestamp(4).toLocalDateTime());
            invoice.setTurn(turnDao.find(resultSet.getInt(5)).orElse(null));
            invoice.setRefund(1 == resultSet.getInt(6));
            invoice.setArchived(1 == resultSet.getInt(7));

            do {
                Product product = new Product();
                product.setProductId(resultSet.getInt(8));
                product.setName(resultSet.getString(9));
                product.setPrice(resultSet.getLong(10));
                product.setUnitName(resultSet.getString(11));
                product.setArchived(1 == resultSet.getInt(12));
                products.put(product, resultSet.getLong(13));
            } while (resultSet.next());
        }
        return invoice;
    }
}

