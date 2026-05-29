package com.example.catalog.adapters.graphql.dto.input;

public record AddReviewInput(
    String productId,
    int rating,
    String comment
) {}
