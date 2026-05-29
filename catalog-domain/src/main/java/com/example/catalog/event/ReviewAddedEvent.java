package com.example.catalog.event;



public class ReviewAddedEvent extends DomainEvent {

    private final String reviewId;
    private final String productId;

    public ReviewAddedEvent(String reviewId, String productId) {
        this.reviewId = reviewId;
        this.productId = productId;
    }

    public String reviewId() {
        return reviewId;
    }

    public String productId() {
        return productId;
    }
}
