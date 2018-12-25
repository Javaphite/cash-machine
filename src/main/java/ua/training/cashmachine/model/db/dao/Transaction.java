package ua.training.cashmachine.model.db.dao;



public interface Transaction extends AutoCloseable {

    void commitTransaction() throws Exception;

    void rollbackTransaction() throws Exception;
}
