package com.example.catalog.adapters.graphql.dto.output;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponse(
    String id,
    String name,
    String description,
    BigDecimal price,
    CategoryResponse category,

    Double averageRating
) {}
