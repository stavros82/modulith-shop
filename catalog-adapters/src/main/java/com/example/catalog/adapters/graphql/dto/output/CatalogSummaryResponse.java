package com.example.catalog.adapters.graphql.dto.output;

import java.math.BigDecimal;

public record CatalogSummaryResponse(
        int totalProducts,
        int totalCategories,
        int totalReviews,
        int totalQualityIssues,
        BigDecimal averageProductPrice,
        Double overallAverageRating
) {}
