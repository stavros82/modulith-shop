package com.example.catalog.model;

import java.util.List;

public record CatalogReport(
        CatalogSummary summary,
        List<ProductStatistics> productStats,
        List<CategoryStatistics> categoryStats
) {}
