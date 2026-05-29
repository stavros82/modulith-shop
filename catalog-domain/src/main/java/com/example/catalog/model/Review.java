package com.example.catalog.model;

import java.time.OffsetDateTime;

public record Review(
        String id,
        int rating,
        String comment,
        String productId,
        OffsetDateTime createdAt
) {}
