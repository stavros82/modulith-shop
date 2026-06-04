package com.example.catalog.adapters.in.graphql.dto.output;

import java.math.BigDecimal;

public record CategoryStatisticsResponse(
        String categoryId,
        String categoryName,
        int productCount,
        int reviewCount,
        Double averageRating,
        BigDecimal averageProductPrice,
        int qualityIssueCount
) {}
