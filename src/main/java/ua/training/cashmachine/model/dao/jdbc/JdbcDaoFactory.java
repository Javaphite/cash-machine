package ua.training.cashmachine.model.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.cashmachine.model.dao.DaoFactory;
import ua.training.cashmachine.model.dao.InvoiceDao;
import ua.training.cashmachine.model.dao.ProductDao;
import ua.training.cashmachine.model.dao.SupplyDao;
import ua.training.cashmachine.model.dao.Transaction;
import ua.training.cashmachine.model.dao.TurnDao;
import ua.training.cashmachine.model.dao.UserDao;
import ua.training.cashmachine.model.dao.mapper.InvoiceMapper;
import ua.training.cashmachine.model.dao.mapper.ProductMapper;
import ua.training.cashmachine.model.dao.mapper.SupplyMapper;
import ua.training.cashmachine.model.dao.mapper.TurnMapper;
import ua.training.cashmachine.model.dao.mapper.UserMapper;

import java.util.Locale;

//TODO: doc me
public final class JdbcDaoFactory implements DaoFactory {

    private static final Logger LOG = LoggerFactory.getLogger(JdbcDaoFactory.class);

    private static volatile DaoFactory daoFactory;

    private JdbcDaoFactory() {}

    //TODO: log me
    public static DaoFactory getInstance() {
        if (null == daoFactory) {
            synchronized (JdbcDaoFactory.class) {
                if (null == daoFactory) {
                    daoFactory = new JdbcDaoFactory();
                }
            }
        }
        return daoFactory;
    }

    @Override
    public Transaction getTransaction() {
        return new JdbcTransaction(JdbcDataSourceConfiguration.getInstance().getConnection());
    }

    @Override
    public UserDao getUserDao(UserMapper mapper, Locale locale) {
        return new JdbcUserDao(JdbcDataSourceConfiguration.getInstance().getConnection(), mapper, locale);
    }

    @Override
    public UserDao getUserDao(UserMapper mapper, Locale locale, Transaction transaction) {
        return new JdbcUserDao((JdbcTransaction) transaction, mapper, locale);
    }

    @Override
    public TurnDao getTurnDao(TurnMapper mapper, Locale locale) {
        return new JdbcTurnDao(JdbcDataSourceConfiguration.getInstance().getConnection(), mapper, locale);
    }

    @Override
    public TurnDao getTurnDao(TurnMapper mapper, Locale locale, Transaction transaction) {
        return new JdbcTurnDao((JdbcTransaction) transaction, mapper, locale);
    }

    @Override
    public ProductDao getProductDao(ProductMapper mapper, Locale locale) {
        return null;//new JdbcProductDao(JdbcDataSourceConfiguration.getInstance().getConnection(), mapper, locale);
    }

    @Override
    public ProductDao getProductDao(ProductMapper mapper, Locale locale, Transaction transaction) {
        return null;//new JdbcProductDao((JdbcTransaction) transaction, mapper, locale);
    }

    @Override
    public InvoiceDao getInvoiceDao(InvoiceMapper mapper, Locale locale) {
        return null;
    }

    @Override
    public InvoiceDao getInvoiceDao(InvoiceMapper mapper, Locale locale, Transaction transaction) {
        return null;
    }

    @Override
    public SupplyDao getSupplyDao(SupplyMapper mapper, Locale locale) {
        return null;
    }

    @Override
    public SupplyDao getSupplyDao(SupplyMapper mapper, Locale locale, Transaction transaction) {
        return null;
    }
}
