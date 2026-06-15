package com.example.catalog.event;



public class ProductCreatedEvent extends DomainEvent {

    private  String productId;

    public ProductCreatedEvent() {
    }

    public ProductCreatedEvent(String productId) {
        this.productId = productId;
    }

    public String productId() {
        return productId;
    }
}
