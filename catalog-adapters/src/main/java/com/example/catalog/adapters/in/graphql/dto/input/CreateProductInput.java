package com.example.catalog.adapters.in.graphql.dto.input;

import java.math.BigDecimal;

public record CreateProductInput(
    String name,
    String description,
    BigDecimal price,
    String categoryId
) {}
