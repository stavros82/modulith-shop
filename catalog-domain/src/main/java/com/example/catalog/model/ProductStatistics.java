package com.example.catalog.model;

import java.math.BigDecimal;

public record ProductStatistics(
        String productId,
        String productName,
        String categoryId,
        String categoryName,
        BigDecimal price,
        int reviewCount,
        Double averageRating,
        int qualityIssueCount
) {}
