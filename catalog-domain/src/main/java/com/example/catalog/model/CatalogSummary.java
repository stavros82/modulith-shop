package com.example.catalog.model;

import java.math.BigDecimal;

public record CatalogSummary(
        int totalProducts,
        int totalCategories,
        int totalReviews,
        int totalQualityIssues,
        BigDecimal averageProductPrice,
        Double overallAverageRating
) {}
