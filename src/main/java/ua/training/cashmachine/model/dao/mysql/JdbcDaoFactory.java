package ua.training.cashmachine.model.dao.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.cashmachine.model.dao.DaoFactory;
import ua.training.cashmachine.model.dao.TurnDao;
import ua.training.cashmachine.model.dao.UserDao;

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
    public UserDao getUserDao(Locale locale) {
        return new JdbcUserDao(JdbcDataSourceConfiguration.getInstance(), locale);
    }

    @Override
    public TurnDao getTurnDao(Locale locale) {
        return new JdbcTurnDao(JdbcDataSourceConfiguration.getInstance(), getUserDao(locale));
    }
}
