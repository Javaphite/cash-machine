package ua.training.cashmachine.model.dao;

import ua.training.cashmachine.model.entity.Invoice;

public interface InvoiceDao extends GenericDao<Invoice> {

    void updateProductsInInvoice(Invoice invoice);
}
