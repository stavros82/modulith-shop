package com.example.catalog.adapters.in.graphql.mapper;

import com.example.catalog.adapters.in.graphql.dto.output.ReviewResponse;
import com.example.catalog.model.Review;

public class ReviewMapper {
    private ReviewMapper() {
        /* This utility class should not be instantiated */
    }


    public static ReviewResponse toResponse(Review e) {
        return new ReviewResponse(
            e.id(),
            e.rating(),
            e.comment(),
            e.productId(),
            e.createdAt()
        );
    }
}
