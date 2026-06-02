package com.example.catalog.adapters.graphql.dto.output;

import java.util.List;

public record CatalogReportResponse(
        CatalogSummaryResponse summary,
        List<ProductStatisticsResponse> productStats,
        List<CategoryStatisticsResponse> categoryStats
) {}
