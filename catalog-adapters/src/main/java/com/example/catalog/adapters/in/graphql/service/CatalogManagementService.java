package com.example.catalog.adapters.in.graphql.service;

import com.example.catalog.model.Product;
import com.example.catalog.model.Category;
import com.example.catalog.model.Review;
import com.example.catalog.model.QualityIssue;
import com.example.catalog.service.CreateProductUseCase;
import com.example.catalog.service.UpdateProductUseCase;
import com.example.catalog.service.AddReviewUseCase;
import com.example.catalog.service.CreateCategoryUseCase;
import com.example.catalog.service.ReportQualityIssueUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CatalogManagementService {

    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final AddReviewUseCase addReviewUseCase;
    private final CreateCategoryUseCase createCategoryUseCase;
    private final ReportQualityIssueUseCase reportQualityIssueUseCase;

    public CatalogManagementService(CreateProductUseCase createProductUseCase,
                                    UpdateProductUseCase updateProductUseCase,
                                    AddReviewUseCase addReviewUseCase,
                                    CreateCategoryUseCase createCategoryUseCase,
                                    ReportQualityIssueUseCase reportQualityIssueUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.addReviewUseCase = addReviewUseCase;
        this.createCategoryUseCase = createCategoryUseCase;
        this.reportQualityIssueUseCase = reportQualityIssueUseCase;
    }

    @Transactional
    public Product createProduct(String name, String description, BigDecimal price, String categoryId) {
        return createProductUseCase.execute(name, description, price, categoryId);
    }

    @Transactional
    public Product updateProduct(String id, String name, String description, BigDecimal price, String categoryId) {
        return updateProductUseCase.execute(id, name, description, price, categoryId);
    }

    @Transactional
    public Review addReview(String productId, int rating, String comment) {
        return addReviewUseCase.execute(productId, rating, comment);
    }

    @Transactional
    public Category createCategory(String name, String parentId) {
        return createCategoryUseCase.execute(name, parentId);
    }

    @Transactional
    public QualityIssue reportQualityIssue(String productId, String type, String severity) {
        return reportQualityIssueUseCase.execute(productId, type, severity);
    }
}
