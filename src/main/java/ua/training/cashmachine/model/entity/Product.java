package ua.training.cashmachine.model.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ua.training.cashmachine.model.annotation.Localized;

import java.util.Objects;

public class Product {

    private int productId;
    @Localized private String name;
    private long price;
    @Localized private String unitName;
    private boolean archived;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if ( (null == obj) || !Objects.equals(getClass(), obj.getClass())) {
            return false;
        }

        Product product = (Product) obj;

        return new EqualsBuilder()
                .append(price, product.price)
                .append(archived, product.archived)
                .append(name, product.name)
                .append(unitName, product.unitName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(price)
                .append(unitName)
                .append(archived)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("productId", productId)
                .append("name", name)
                .append("price", price)
                .append("unitName", unitName)
                .append("archived", archived)
                .toString();
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}
