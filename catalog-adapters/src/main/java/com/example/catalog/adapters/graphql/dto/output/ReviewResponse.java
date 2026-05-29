package com.example.catalog.adapters.graphql.dto.output;

import java.time.OffsetDateTime;

public record ReviewResponse(
    String id,
    int rating,
    String comment,
    String productId,
    OffsetDateTime createdAt
) {}
