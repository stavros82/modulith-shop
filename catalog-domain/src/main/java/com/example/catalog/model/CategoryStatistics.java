package com.example.catalog.model;

import java.math.BigDecimal;

public record CategoryStatistics(
        String categoryId,
        String categoryName,
        int productCount,
        int reviewCount,
        Double averageRating,
        BigDecimal averageProductPrice,
        int qualityIssueCount
) {}
