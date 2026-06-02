package com.example.inventory.event;

import java.math.BigDecimal;

public class StockNotAvailableEvent {

    private final String orderId;
    private final String productId;
    private final BigDecimal requestedQuantity;
    private final BigDecimal availableQuantity;

    public StockNotAvailableEvent(String orderId, String productId, BigDecimal requestedQuantity, BigDecimal availableQuantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.requestedQuantity = requestedQuantity;
        this.availableQuantity = availableQuantity;
    }

    public String orderId() {
        return orderId;
    }

    public String productId() {
        return productId;
    }

    public BigDecimal requestedQuantity() {
        return requestedQuantity;
    }

    public BigDecimal availableQuantity() {
        return availableQuantity;
    }
}

