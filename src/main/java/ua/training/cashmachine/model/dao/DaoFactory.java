package ua.training.cashmachine.model.dao;

import ua.training.cashmachine.model.dao.mapper.InvoiceMapper;
import ua.training.cashmachine.model.dao.mapper.ProductMapper;
import ua.training.cashmachine.model.dao.mapper.SupplyMapper;
import ua.training.cashmachine.model.dao.mapper.TurnMapper;
import ua.training.cashmachine.model.dao.mapper.UserMapper;

import java.util.Locale;

public interface DaoFactory {

    Transaction getTransaction();

    UserDao getUserDao(UserMapper mapper, Locale locale);

    UserDao getUserDao(UserMapper mapper, Locale locale, Transaction transaction);

    TurnDao getTurnDao(TurnMapper mapper, Locale locale);

    TurnDao getTurnDao(TurnMapper mapper, Locale locale, Transaction transaction);

    ProductDao getProductDao(ProductMapper mapper, Locale locale);

    ProductDao getProductDao(ProductMapper mapper, Locale locale, Transaction transaction);

    InvoiceDao getInvoiceDao(InvoiceMapper mapper, Locale locale);

    InvoiceDao getInvoiceDao(InvoiceMapper mapper, Locale locale, Transaction transaction);

    SupplyDao getSupplyDao(SupplyMapper mapper, Locale locale);

    SupplyDao getSupplyDao(SupplyMapper mapper, Locale locale, Transaction transaction);
}
