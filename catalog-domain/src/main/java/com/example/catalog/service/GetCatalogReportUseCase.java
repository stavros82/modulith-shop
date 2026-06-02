package com.example.catalog.service;

import com.example.catalog.model.*;
import com.example.catalog.repository.CategoryRepository;
import com.example.catalog.repository.ProductRepository;
import com.example.catalog.repository.QualityIssueRepository;
import com.example.catalog.repository.ReviewRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetCatalogReportUseCase {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ReviewRepository reviewRepository;
    private final QualityIssueRepository qualityIssueRepository;

    public GetCatalogReportUseCase(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            ReviewRepository reviewRepository,
            QualityIssueRepository qualityIssueRepository
    ) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.reviewRepository = reviewRepository;
        this.qualityIssueRepository = qualityIssueRepository;
    }

    public CatalogReport execute() {
        List<Product> products = productRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        Map<String, Category> categoryById = new HashMap<>();
        for (Category category : categories) {
            categoryById.put(category.id(), category);
        }

        List<ProductStatistics> productStats = new ArrayList<>();
        Map<String, CategoryAccumulator> categoryAccumulators = new HashMap<>();
        for (Category category : categories) {
            categoryAccumulators.put(category.id(), new CategoryAccumulator(category.id(), category.name()));
        }

        int totalReviews = 0;
        int totalQualityIssues = 0;
        BigDecimal priceSum = BigDecimal.ZERO;
        int ratingCount = 0;
        int ratingSum = 0;

        for (Product product : products) {
            List<Review> reviews = reviewRepository.findByProductId(product.id());
            List<QualityIssue> issues = qualityIssueRepository.findByProductId(product.id());

            int reviewCount = reviews.size();
            int issueCount = issues.size();
            Double averageRating = averageRating(reviews);

            String categoryId = product.categoryId();
            String categoryName = categoryId != null
                    ? categoryById.getOrDefault(categoryId, new Category(categoryId, "Unknown", null)).name()
                    : null;

            productStats.add(new ProductStatistics(
                    product.id(),
                    product.name(),
                    categoryId,
                    categoryName,
                    product.price(),
                    reviewCount,
                    averageRating,
                    issueCount
            ));

            totalReviews += reviewCount;
            totalQualityIssues += issueCount;
            if (product.price() != null) {
                priceSum = priceSum.add(product.price());
            }
            for (Review review : reviews) {
                ratingSum += review.rating();
                ratingCount++;
            }

            if (categoryId != null && categoryAccumulators.containsKey(categoryId)) {
                categoryAccumulators.get(categoryId).add(product, reviews, issues);
            }
        }

        List<CategoryStatistics> categoryStats = categoryAccumulators.values().stream()
                .map(CategoryAccumulator::toStatistics)
                .toList();

        CatalogSummary summary = new CatalogSummary(
                products.size(),
                categories.size(),
                totalReviews,
                totalQualityIssues,
                averagePrice(priceSum, products.size()),
                overallAverageRating(ratingSum, ratingCount)
        );

        return new CatalogReport(summary, productStats, categoryStats);
    }

    private static Double averageRating(List<Review> reviews) {
        if (reviews.isEmpty()) {
            return null;
        }
        return reviews.stream()
                .mapToInt(Review::rating)
                .average()
                .orElse(0);
    }

    private static BigDecimal averagePrice(BigDecimal priceSum, int productCount) {
        if (productCount == 0) {
            return null;
        }
        return priceSum.divide(BigDecimal.valueOf(productCount), 2, RoundingMode.HALF_UP);
    }

    private static Double overallAverageRating(int ratingSum, int ratingCount) {
        if (ratingCount == 0) {
            return null;
        }
        return (double) ratingSum / ratingCount;
    }

    private static final class CategoryAccumulator {
        private final String categoryId;
        private final String categoryName;
        private int productCount;
        private int reviewCount;
        private int qualityIssueCount;
        private int ratingSum;
        private int ratingCount;
        private BigDecimal priceSum = BigDecimal.ZERO;

        private CategoryAccumulator(String categoryId, String categoryName) {
            this.categoryId = categoryId;
            this.categoryName = categoryName;
        }

        private void add(Product product, List<Review> reviews, List<QualityIssue> issues) {
            productCount++;
            reviewCount += reviews.size();
            qualityIssueCount += issues.size();
            if (product.price() != null) {
                priceSum = priceSum.add(product.price());
            }
            for (Review review : reviews) {
                ratingSum += review.rating();
                ratingCount++;
            }
        }

        private CategoryStatistics toStatistics() {
            return new CategoryStatistics(
                    categoryId,
                    categoryName,
                    productCount,
                    reviewCount,
                    ratingCount == 0 ? null : (double) ratingSum / ratingCount,
                    averagePrice(priceSum, productCount),
                    qualityIssueCount
            );
        }
    }
}
