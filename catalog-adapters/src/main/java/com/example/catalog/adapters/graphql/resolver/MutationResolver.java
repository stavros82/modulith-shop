package com.example.catalog.adapters.graphql.resolver;

import com.example.catalog.event.ProductCreatedEvent;
import com.example.catalog.event.ProductUpdatedEvent;
import com.example.catalog.event.QualityIssueReportedEvent;
import com.example.catalog.adapters.graphql.dto.input.AddReviewInput;
import com.example.catalog.adapters.graphql.dto.input.CreateCategoryInput;
import com.example.catalog.adapters.graphql.dto.input.CreateProductInput;
import com.example.catalog.adapters.graphql.dto.input.ReportQualityIssueInput;
import com.example.catalog.adapters.graphql.dto.input.UpdateProductInput;
import com.example.catalog.adapters.graphql.dto.output.CategoryResponse;
import com.example.catalog.adapters.graphql.dto.output.ProductResponse;
import com.example.catalog.adapters.graphql.dto.output.QualityIssueResponse;
import com.example.catalog.adapters.graphql.dto.output.ReviewResponse;
import com.example.catalog.adapters.graphql.mapper.CategoryMapper;
import com.example.catalog.adapters.graphql.mapper.ProductMapper;
import com.example.catalog.adapters.graphql.mapper.QualityIssueMapper;
import com.example.catalog.adapters.graphql.mapper.ReviewMapper;
import com.example.catalog.service.AddReviewUseCase;
import com.example.catalog.service.CreateCategoryUseCase;
import com.example.catalog.service.CreateProductUseCase;
import com.example.catalog.service.ReportQualityIssueUseCase;
import com.example.catalog.service.UpdateProductUseCase;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MutationResolver {

    private final CreateCategoryUseCase createCategory;
    private final CreateProductUseCase createProduct;
    private final UpdateProductUseCase updateProduct;
    private final AddReviewUseCase addReview;
    private final ReportQualityIssueUseCase reportIssue;
    private final ApplicationEventPublisher eventPublisher;

    public MutationResolver(
            CreateCategoryUseCase createCategory,
            CreateProductUseCase createProduct,
            UpdateProductUseCase updateProduct,
            AddReviewUseCase addReview,
            ReportQualityIssueUseCase reportIssue,
            ApplicationEventPublisher eventPublisher
    ) {
        this.createCategory = createCategory;
        this.createProduct = createProduct;
        this.updateProduct = updateProduct;
        this.addReview = addReview;
        this.reportIssue = reportIssue;
        this.eventPublisher = eventPublisher;
    }

    @MutationMapping
    public CategoryResponse createCategory(@Argument("input") CreateCategoryInput input) {
        var category = createCategory.execute(input.name(), input.parentId());
        return CategoryMapper.toResponse(category);
    }

    @MutationMapping
    public ProductResponse createProduct(@Argument("input") CreateProductInput input) {
        var product = createProduct.execute(
                input.name(),
                input.description(),
                input.price(),
                input.categoryId()
        );

        System.out.println("USE CASE RETURNED = " + product);

        eventPublisher.publishEvent(new ProductCreatedEvent(product.id()));
        return ProductMapper.toResponse(product);
    }

    @MutationMapping
    public ProductResponse updateProduct(@Argument("input") UpdateProductInput input) {
        var product = updateProduct.execute(
                input.id(),
                input.name(),
                input.description(),
                input.price(),
                input.categoryId()
        );
        eventPublisher.publishEvent(new ProductUpdatedEvent(product.id()));
        return ProductMapper.toResponse(product);
    }

    @MutationMapping
    public ReviewResponse addReview(@Argument("input") AddReviewInput input) {
        var review = addReview.execute(
                input.productId(),
                input.rating(),
                input.comment()
        );
        return ReviewMapper.toResponse(review);
    }

    @MutationMapping
    public QualityIssueResponse reportQualityIssue(@Argument("input")ReportQualityIssueInput input) {
        var issue = reportIssue.execute(
                input.productId(),
                input.type(),
                input.severity()
        );
        eventPublisher.publishEvent(new QualityIssueReportedEvent(issue.id(), issue.productId()));
        return QualityIssueMapper.toResponse(issue);
    }
}
