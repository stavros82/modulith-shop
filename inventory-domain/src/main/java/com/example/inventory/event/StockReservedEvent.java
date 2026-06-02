package com.example.inventory.event;

import java.math.BigDecimal;

public class StockReservedEvent {

    private final String orderId;
    private final String productId;
    private final BigDecimal quantity;

    public StockReservedEvent(String orderId, String productId, BigDecimal quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String orderId() {
        return orderId;
    }

    public String productId() {
        return productId;
    }

    public BigDecimal quantity() {
        return quantity;
    }
}

