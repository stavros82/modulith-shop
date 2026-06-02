package com.example.catalog.adapters.graphql.dto.output;

import java.math.BigDecimal;

public record ProductStatisticsResponse(
        String productId,
        String productName,
        String categoryId,
        String categoryName,
        BigDecimal price,
        int reviewCount,
        Double averageRating,
        int qualityIssueCount
) {}
