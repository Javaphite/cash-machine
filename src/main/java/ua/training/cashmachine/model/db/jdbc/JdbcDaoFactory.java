package ua.training.cashmachine.model.db.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.cashmachine.model.db.dao.DaoFactory;
import ua.training.cashmachine.model.db.dao.Transaction;
import ua.training.cashmachine.model.db.dao.TurnDao;
import ua.training.cashmachine.model.db.dao.UserDao;
import ua.training.cashmachine.model.db.mapper.GenericMapper;
import ua.training.cashmachine.model.db.mapper.UserMapper;
import ua.training.cashmachine.model.entity.Turn;
import ua.training.cashmachine.model.entity.User;

import java.sql.Connection;
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

    /*@Override
    public TurnDao getTurnDao(GenericMapper<Turn> mapper, Locale locale) {
        return new JdbcTurnDao(JdbcDataSourceConfiguration.getInstance(), getUserDao(locale));
    }*/
}
