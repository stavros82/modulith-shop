package com.example.shared;

public class ProductUpdatedEvent extends DomainEvent {
    private final String productId;

    public ProductUpdatedEvent(String productId) {
        this.productId = productId;
    }

    public String productId() {
        return productId;
    }
}
