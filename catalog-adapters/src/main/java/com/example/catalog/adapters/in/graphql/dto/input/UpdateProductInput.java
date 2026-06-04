package com.example.catalog.adapters.in.graphql.dto.input;

import java.math.BigDecimal;

public record UpdateProductInput(
    String id,
    String name,
    String description,
    BigDecimal price,
    String categoryId
) {}
