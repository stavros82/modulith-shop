package com.example.inventory.adapters.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "inventory_products")
public class InventoryProductJpaEntity {

    @Id
    private String productId;

    private String productName;

    private String safetyStatus;

    private BigDecimal stockQuantity;
    private BigDecimal reservedQuantity;
    private BigDecimal quarantinedQuantity;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSafetyStatus() {
        return safetyStatus;
    }

    public void setSafetyStatus(String safetyStatus) {
        this.safetyStatus = safetyStatus;
    }

    public BigDecimal getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(BigDecimal stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public BigDecimal getReservedQuantity() {
        return reservedQuantity;
    }

    public void setReservedQuantity(BigDecimal reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
    }

    public BigDecimal getQuarantinedQuantity() {
        return quarantinedQuantity;
    }

    public void setQuarantinedQuantity(BigDecimal quarantinedQuantity) {
        this.quarantinedQuantity = quarantinedQuantity;
    }
}

