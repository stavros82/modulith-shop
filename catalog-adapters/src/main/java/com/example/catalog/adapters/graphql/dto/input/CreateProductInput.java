package com.example.catalog.adapters.graphql.dto.input;

import java.math.BigDecimal;

public record CreateProductInput(
    String name,
    String description,
    BigDecimal price,
    String categoryId
) {}
