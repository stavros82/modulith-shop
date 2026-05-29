package com.example.catalog.adapters.config;


import com.example.catalog.repository.ProductRepository;
import com.example.catalog.repository.QualityIssueRepository;
import com.example.catalog.repository.ReviewRepository;
import com.example.catalog.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateProductUseCase createProductUseCase(ProductRepository productRepository) {
        return new CreateProductUseCase(productRepository);
    }

    @Bean
    public UpdateProductUseCase updateProductUseCase(ProductRepository productRepository) {
        return new UpdateProductUseCase(productRepository);
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
    public ReportQualityIssueUseCase reportQualityIssueUseCase(QualityIssueRepository qualityIssueRepository) {
        return new ReportQualityIssueUseCase(qualityIssueRepository);
    }
}
