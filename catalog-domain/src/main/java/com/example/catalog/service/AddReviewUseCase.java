package com.example.catalog.service;


import com.example.catalog.model.Product;
import com.example.catalog.model.Review;
import com.example.catalog.repository.ProductRepository;
import com.example.catalog.repository.ReviewRepository;

import java.time.OffsetDateTime;
import java.util.UUID;

public class AddReviewUseCase {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public AddReviewUseCase(ProductRepository productRepository, ReviewRepository reviewRepository) {
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
    }

    public Review execute(String productId, int rating, String comment) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));

        Review review = new Review(
                UUID.randomUUID().toString(),
                rating,
                comment,
                productId,
                OffsetDateTime.now()
        );

        product.addReview(review);
        reviewRepository.save(review);

        productRepository.save(product);

        return review;
    }
}
