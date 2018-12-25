package ua.training.cashmachine.model.dao;



public interface Transaction extends AutoCloseable {

    void commitTransaction() throws Exception;

    void rollbackTransaction() throws Exception;
}
