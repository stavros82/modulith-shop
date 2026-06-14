package com.example.catalog.adapters.config;


import com.example.catalog.event.ProductEventPublisher;
import com.example.catalog.repository.CategoryRepository;
import com.example.catalog.repository.ProductRepository;
import com.example.catalog.repository.QualityIssueRepository;
import com.example.catalog.repository.ReviewRepository;
import com.example.catalog.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatalogUseCaseConfig {

    @Bean
    public CreateCategoryUseCase createCategoryUseCase(CategoryRepository categoryRepository) {
        return new CreateCategoryUseCase(categoryRepository);
    }

    @Bean
    public CreateProductUseCase createProductUseCase(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            ProductEventPublisher eventPublisher
    ) {
        return new CreateProductUseCase(productRepository, categoryRepository, eventPublisher);
    }

    @Bean
    public UpdateProductUseCase updateProductUseCase(
            ProductRepository productRepository,
            CategoryRepository categoryRepository
    ) {
        return new UpdateProductUseCase(productRepository, categoryRepository);
    }

    @Bean
    public GetProductUseCase getProductUseCase(ProductRepository productRepository) {
        return new GetProductUseCase(productRepository);
    }

    @Bean
    public ListProductsUseCase listProductsUseCase(ProductRepository productRepository) {
        return new ListProductsUseCase(productRepository);
    }

    @Bean
    public AddReviewUseCase addReviewUseCase(ProductRepository productRepository, ReviewRepository reviewRepository) {
        return new AddReviewUseCase(productRepository, reviewRepository);
    }

    @Bean
    public ReportQualityIssueUseCase reportQualityIssueUseCase(
            QualityIssueRepository qualityIssueRepository,
            ProductEventPublisher eventPublisher
    ) {
        return new ReportQualityIssueUseCase(qualityIssueRepository, eventPublisher);
    }

    @Bean
    public GetCatalogReportUseCase getCatalogReportUseCase(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            ReviewRepository reviewRepository,
            QualityIssueRepository qualityIssueRepository
    ) {
        return new GetCatalogReportUseCase(
                productRepository,
                categoryRepository,
                reviewRepository,
                qualityIssueRepository
        );
    }
}
