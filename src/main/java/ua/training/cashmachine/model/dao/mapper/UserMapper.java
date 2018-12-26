package ua.training.cashmachine.model.dao.mapper;

import ua.training.cashmachine.model.entity.Role;
import ua.training.cashmachine.model.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class UserMapper implements LocalizationMapper<User> {

    @Override
    public PreparedStatement mapCreate(PreparedStatement statement, User entity) throws SQLException {
        statement.setString(1, entity.getLogin());
        statement.setString(2, entity.getRole().name());
        statement.setString(3, entity.getHash());
        statement.setString(4, entity.getFirstName());
        statement.setString(5, entity.getLastName());
        return statement;
    }

    @Override
    public PreparedStatement mapDelete(PreparedStatement statement, User entity) throws SQLException {
        statement.setInt(1, entity.getUserId());
        return statement;
    }

    @Override
    public PreparedStatement mapUpdate(PreparedStatement statement, User entity) throws SQLException {
        statement.setString(1, entity.getLogin());
        statement.setString(2, entity.getRole().name());
        statement.setString(3, entity.getFirstName());
        statement.setString(4, entity.getLastName());
        statement.setInt(5, entity.isExpired()? 1: 0);
        statement.setInt(6, entity.getUserId());
        return statement;
    }

    @Override
    public User mapFind(ResultSet resultSet) throws SQLException {
        User user = null;
        if (resultSet.next()) {
            user = new User();
            user.setUserId(resultSet.getInt(1));
            user.setLogin(resultSet.getNString(2));
            user.setRole(Role.valueOf(resultSet.getNString(3)));
            user.setFirstName(resultSet.getNString(4));
            user.setLastName(resultSet.getNString(5));
            user.setExpired(resultSet.getInt(6)==1);
            user.setHash(User.DEFAULT_HASH);
        }
        return user;
    }

    @Override
    public PreparedStatement mapLocalization(PreparedStatement statement, User entity,
                                             Map<Locale, Map<String, String>> localizationTable) throws SQLException {
        Map<String, String> enValues = localizationTable.get(Locale.forLanguageTag("uk-UA"));
        Map<String, String> ukValues = localizationTable.get(Locale.forLanguageTag("en-US"));
        if(Objects.nonNull(enValues) && Objects.nonNull(ukValues)) {
            statement.setString(1, ukValues.get("firstName"));
            statement.setString(2, ukValues.get("lastName"));
            statement.setString(3, enValues.get("firstName"));
            statement.setString(4, enValues.get("lastName"));
            statement.setInt(5, entity.getUserId());
        }
        return statement;
    }
}
