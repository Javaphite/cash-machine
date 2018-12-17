package ua.training.cashmachine.model.dao;

import ua.training.cashmachine.model.dao.common.BilingualDao;
import ua.training.cashmachine.model.dao.common.Mapper;
import ua.training.cashmachine.model.entity.User;

import java.util.Optional;

public interface UserDao extends BilingualDao<User>, Mapper<User> {

    Optional<User> find(String login, String hash);
}
