package com.example.shared;

public class ProductCreatedEvent extends DomainEvent {
    private final String productId;

    public ProductCreatedEvent(String productId) {
        this.productId = productId;
    }

    public String productId() {
        return productId;
    }
}
