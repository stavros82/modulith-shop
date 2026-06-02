package com.example.inventory.model;

import java.math.BigDecimal;

public class InventoryProduct {

    private final String productId;
    private String productName;
    private ProductSafetyStatus safetyStatus;

    private BigDecimal stockQuantity;
    private BigDecimal reservedQuantity;
    private BigDecimal quarantinedQuantity;

    public InventoryProduct(String productId, String productName, ProductSafetyStatus safetyStatus) {
        this(productId, productName, safetyStatus, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
    }

    public InventoryProduct(
            String productId,
            String productName,
            ProductSafetyStatus safetyStatus,
            BigDecimal stockQuantity,
            BigDecimal reservedQuantity,
            BigDecimal quarantinedQuantity
    ) {
        this.productId = productId;
        this.productName = productName;
        this.safetyStatus = safetyStatus;
        this.stockQuantity = stockQuantity == null ? BigDecimal.ZERO : stockQuantity;
        this.reservedQuantity = reservedQuantity == null ? BigDecimal.ZERO : reservedQuantity;
        this.quarantinedQuantity = quarantinedQuantity == null ? BigDecimal.ZERO : quarantinedQuantity;
    }

    public String productId() {
        return productId;
    }

    public String productName() {
        return productName;
    }

    public ProductSafetyStatus safetyStatus() {
        return safetyStatus;
    }

    public BigDecimal stockQuantity() {
        return stockQuantity;
    }

    public BigDecimal reservedQuantity() {
        return reservedQuantity;
    }

    public BigDecimal quarantinedQuantity() {
        return quarantinedQuantity;
    }

    public BigDecimal availableQuantity() {
        // available = stock - reserved - quarantined
        return stockQuantity.subtract(reservedQuantity).subtract(quarantinedQuantity).max(BigDecimal.ZERO);
    }

    public void rename(String newName) {
        if (newName != null && !newName.isBlank()) {
            this.productName = newName;
        }
    }

    public void markActive() {
        this.safetyStatus = ProductSafetyStatus.ACTIVE;
    }

    public void markUnsafeAndQuarantineAll() {
        this.safetyStatus = ProductSafetyStatus.UNSAFE;
        this.quarantinedQuantity = this.stockQuantity;
    }

    public boolean canReserve() {
        return safetyStatus == ProductSafetyStatus.ACTIVE;
    }

    public boolean tryReserve(java.math.BigDecimal quantity) {
        if (quantity == null || quantity.signum() <= 0) {
            return false;
        }
        if (!canReserve()) {
            return false;
        }
        if (availableQuantity().compareTo(quantity) < 0) {
            return false;
        }
        this.reservedQuantity = this.reservedQuantity.add(quantity);
        return true;
    }

    public void replenish(BigDecimal quantity) {
        if (quantity == null || quantity.signum() <= 0) {
            return;
        }
        this.stockQuantity = this.stockQuantity.add(quantity);
        if (this.safetyStatus == ProductSafetyStatus.UNSAFE) {
            // if product is unsafe, new incoming stock is also quarantined
            this.quarantinedQuantity = this.quarantinedQuantity.add(quantity);
        }
    }
}

