package com.example.catalog.adapters.in.graphql.dto.input;

public record AddReviewInput(
    String productId,
    int rating,
    String comment
) {}
