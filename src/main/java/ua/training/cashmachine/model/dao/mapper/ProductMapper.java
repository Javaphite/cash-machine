package ua.training.cashmachine.model.dao.mapper;

import ua.training.cashmachine.model.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

//TODO: implement me
public class ProductMapper implements LocalizationMapper<Product> {

    @Override
    public PreparedStatement mapCreate(PreparedStatement statement, Product entity) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement mapUpdate(PreparedStatement statement, Product entity) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement mapDelete(PreparedStatement statement, Product entity) throws SQLException {
        return null;
    }

    @Override
    public Product mapFind(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement mapLocalization(PreparedStatement statement, Product entity,
                                             Map<Locale, Map<String, String>> localizationTable) throws SQLException {
        return null;
    }
}
