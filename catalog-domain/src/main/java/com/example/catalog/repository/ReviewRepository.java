package com.example.catalog.repository;

import com.example.catalog.model.Review;

import java.util.List;

public interface ReviewRepository {

    Review save(Review review);

    List<Review> findByProductId(String productId);
}
