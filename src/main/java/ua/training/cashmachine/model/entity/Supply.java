package ua.training.cashmachine.model.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

public class Supply {

    private int supplyId;
    private Product product;
    private long unitsAvailable;
    private User user;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if ((null == obj) || !Objects.equals(getClass(), obj.getClass())) {
            return false;
        }

        Supply supply = (Supply) obj;

        return new EqualsBuilder()
                .append(unitsAvailable, supply.unitsAvailable)
                .append(product, supply.product)
                .append(user, supply.user)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(product)
                .append(unitsAvailable)
                .append(user)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("supplyId", supplyId)
                .append("product", product)
                .append("unitsAvailable", unitsAvailable)
                .append("user", user)
                .toString();
    }

    public int getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(int supplyId) {
        this.supplyId = supplyId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getSupply() {
        return unitsAvailable;
    }

    public void setSupply(long unitsAvailable) {
        this.unitsAvailable = unitsAvailable;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
