package com.example.catalog.adapters.in.graphql.dto.output;

import java.math.BigDecimal;

public record ProductResponse(
    String id,
    String name,
    String description,
    BigDecimal price,
    CategoryResponse category,

    Double averageRating
) {}
