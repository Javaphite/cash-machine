package ua.training.cashmachine.model.db.dao;



public interface Transaction {

    void append(GenericDao<?> dao);

    void commitTransaction() throws Exception;

    void rollbackTransaction() throws Exception;
}
