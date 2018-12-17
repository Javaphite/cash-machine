package ua.training.cashmachine.model.dao;

import ua.training.cashmachine.model.dao.common.Mapper;
import ua.training.cashmachine.model.dao.common.SimpleDao;
import ua.training.cashmachine.model.dao.common.TransactionAware;
import ua.training.cashmachine.model.entity.Turn;

public interface TurnDao extends SimpleDao<Turn>, Mapper<Turn> {

}
