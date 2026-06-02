package com.example.catalog.adapters.graphql.mapper;

import com.example.catalog.adapters.graphql.dto.output.*;
import com.example.catalog.model.*;

public class CatalogReportMapper {

    private CatalogReportMapper() {}

    public static CatalogReportResponse toResponse(CatalogReport report) {
        return new CatalogReportResponse(
                toSummaryResponse(report.summary()),
                report.productStats().stream().map(CatalogReportMapper::toProductStatsResponse).toList(),
                report.categoryStats().stream().map(CatalogReportMapper::toCategoryStatsResponse).toList()
        );
    }

    private static CatalogSummaryResponse toSummaryResponse(CatalogSummary summary) {
        return new CatalogSummaryResponse(
                summary.totalProducts(),
                summary.totalCategories(),
                summary.totalReviews(),
                summary.totalQualityIssues(),
                summary.averageProductPrice(),
                summary.overallAverageRating()
        );
    }

    private static ProductStatisticsResponse toProductStatsResponse(ProductStatistics stats) {
        return new ProductStatisticsResponse(
                stats.productId(),
                stats.productName(),
                stats.categoryId(),
                stats.categoryName(),
                stats.price(),
                stats.reviewCount(),
                stats.averageRating(),
                stats.qualityIssueCount()
        );
    }

    private static CategoryStatisticsResponse toCategoryStatsResponse(CategoryStatistics stats) {
        return new CategoryStatisticsResponse(
                stats.categoryId(),
                stats.categoryName(),
                stats.productCount(),
                stats.reviewCount(),
                stats.averageRating(),
                stats.averageProductPrice(),
                stats.qualityIssueCount()
        );
    }
}
