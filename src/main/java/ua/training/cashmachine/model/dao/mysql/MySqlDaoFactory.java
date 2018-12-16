package ua.training.cashmachine.model.dao.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.cashmachine.model.dao.DaoFactory;
import ua.training.cashmachine.model.dao.UserDao;

import java.sql.Connection;
import java.util.Locale;

//TODO: doc me
public final class MySqlDaoFactory implements DaoFactory {

    private static final Logger LOG = LoggerFactory.getLogger(MySqlDaoFactory.class);

    private static volatile DaoFactory daoFactory;

    private MySqlDaoFactory() {}

    //TODO: log me
    public static DaoFactory getInstance() {
        if (null == daoFactory) {
            synchronized (MySqlDaoFactory.class) {
                if (null == daoFactory) {
                    daoFactory = new MySqlDaoFactory();
                }
            }
        }
        return daoFactory;
    }

    @Override
    public UserDao getUserDao(Locale locale) {
        return new MySqlUserDao(MySqlConfiguration.getInstance(), locale);
    }
}
