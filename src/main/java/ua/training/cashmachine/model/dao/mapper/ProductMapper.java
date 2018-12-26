package ua.training.cashmachine.model.dao.mapper;

import ua.training.cashmachine.model.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class ProductMapper implements LocalizationMapper<Product> {

    @Override
    public PreparedStatement mapCreate(PreparedStatement statement, Product entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setLong(2, entity.getPrice());
        statement.setString(3, entity.getUnitName());
        return statement;
    }

    @Override
    public PreparedStatement mapUpdate(PreparedStatement statement, Product entity) throws SQLException {
        statement.setLong(1, entity.getPrice());
        statement.setInt(2, entity.isArchived()?1:0);
        statement.setInt(3, entity.getProductId());
        return statement;
    }

    @Override
    public PreparedStatement mapDelete(PreparedStatement statement, Product entity) throws SQLException {
        statement.setInt(1, entity.getProductId());
        return statement;
    }

    @Override
    public Product mapFind(ResultSet resultSet) throws SQLException {
        Product product = null;
        if (resultSet.next()) {
            product = new Product();
            product.setProductId(resultSet.getInt(1));
            product.setName(resultSet.getString(2));
            product.setUnitName(resultSet.getString(3));
            product.setArchived(1 == resultSet.getInt(4));
        }
        return product;
    }

    @Override
    public PreparedStatement mapLocalization(PreparedStatement statement, Product entity,
                                             Map<Locale, Map<String, String>> localizationTable) throws SQLException {
        Map<String, String> enValues = localizationTable.get(Locale.forLanguageTag("uk-UA"));
        Map<String, String> ukValues = localizationTable.get(Locale.forLanguageTag("en-US"));
        if(Objects.nonNull(enValues) && Objects.nonNull(ukValues)) {
            statement.setString(1, ukValues.get("name"));
            statement.setString(2, ukValues.get("unitName"));
            statement.setString(3, enValues.get("name"));
            statement.setString(4, enValues.get("unitName"));
            statement.setInt(5, entity.getProductId());
        }
        return statement;
    }
}
