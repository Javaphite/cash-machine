package ua.training.cashmachine.model.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public class Invoice {

    private int invoiceId;
    private User user;
    private long totalCost;
    private LocalDateTime creationTime;
    private Turn turn;
    private boolean refund;
    private boolean archived;
    private Map<Product, Long> products;

    // ToDO: add builder

    public Map<Product, Long> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Long> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if ((null == obj) || !Objects.equals(getClass(), obj.getClass())) {
            return false;
        }

        Invoice invoice = (Invoice) obj;

        return new EqualsBuilder()
                .append(totalCost, invoice.totalCost)
                .append(refund, invoice.refund)
                .append(archived, invoice.archived)
                .append(user, invoice.user)
                .append(creationTime, invoice.creationTime)
                .append(turn, invoice.turn)
                .append(products, invoice.products)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(user)
                .append(totalCost)
                .append(creationTime)
                .append(turn)
                .append(refund)
                .append(archived)
                .append(products)
                .toHashCode();
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(long totalCost) {
        this.totalCost = totalCost;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    public boolean isRefund() {
        return refund;
    }

    public void setRefund(boolean refund) {
        this.refund = refund;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}
