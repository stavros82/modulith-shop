package com.example.catalog.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product {

    private  String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String categoryId;

    private final List<Review> reviews = new ArrayList<>();

    public Product(String id, String name, String description, BigDecimal price, String categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
    }

    public Product(String id, String name, BigDecimal price, String id1) {
        this.id = id1;
    }

    public Product(String id, String name, BigDecimal price) {
    }

    // -------- Domain Behavior --------

    public void update(String name, String description, BigDecimal price, String categoryId) {
        if (name != null) this.name = name;
        if (description != null) this.description = description;
        if (price != null) this.price = price;
        if (categoryId != null) this.categoryId = categoryId;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public double averageRating() {
        return reviews.stream()
                .mapToInt(Review::rating)
                .average()
                .orElse(0);
    }

    // -------- Getters --------

    public String id() { return id; }
    public String name() { return name; }
    public String description() { return description; }
    public BigDecimal price() { return price; }
    public String categoryId() { return categoryId; }
    public List<Review> reviews() { return reviews; }
}
