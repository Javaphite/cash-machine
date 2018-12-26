package ua.training.cashmachine.model.dao.mapper;

import ua.training.cashmachine.model.entity.Supply;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//TODO: implement me
public class SupplyMapper implements GenericMapper<Supply> {

    @Override
    public PreparedStatement mapCreate(PreparedStatement statement, Supply entity) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement mapUpdate(PreparedStatement statement, Supply entity) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement mapDelete(PreparedStatement statement, Supply entity) throws SQLException {
        return null;
    }

    @Override
    public Supply mapFind(ResultSet resultSet) throws SQLException {
        return null;
    }
}
