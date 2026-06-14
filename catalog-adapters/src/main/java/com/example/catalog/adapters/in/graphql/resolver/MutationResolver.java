package com.example.catalog.adapters.in.graphql.resolver;

import com.example.catalog.adapters.in.graphql.dto.input.AddReviewInput;
import com.example.catalog.adapters.in.graphql.dto.input.CreateCategoryInput;
import com.example.catalog.adapters.in.graphql.dto.input.CreateProductInput;
import com.example.catalog.adapters.in.graphql.dto.input.ReportQualityIssueInput;
import com.example.catalog.adapters.in.graphql.dto.input.UpdateProductInput;
import com.example.catalog.adapters.in.graphql.dto.output.CategoryResponse;
import com.example.catalog.adapters.in.graphql.dto.output.ProductResponse;
import com.example.catalog.adapters.in.graphql.dto.output.QualityIssueResponse;
import com.example.catalog.adapters.in.graphql.dto.output.ReviewResponse;
import com.example.catalog.adapters.in.graphql.mapper.CategoryMapper;
import com.example.catalog.adapters.in.graphql.mapper.ProductMapper;
import com.example.catalog.adapters.in.graphql.mapper.QualityIssueMapper;
import com.example.catalog.adapters.in.graphql.mapper.ReviewMapper;
import com.example.catalog.adapters.in.graphql.service.CatalogManagementService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MutationResolver {

    private final CatalogManagementService catalogManagementService;

    public MutationResolver(
            CatalogManagementService catalogManagementService
    ) {
        this.catalogManagementService = catalogManagementService;
    }

    @MutationMapping
    public CategoryResponse createCategory(@Argument("input") CreateCategoryInput input) {
        var category = catalogManagementService.createCategory(input.name(), input.parentId());
        return CategoryMapper.toResponse(category);
    }

    @MutationMapping
    public ProductResponse createProduct(@Argument("input") CreateProductInput input) {
        var product = catalogManagementService.createProduct(
                input.name(),
                input.description(),
                input.price(),
                input.categoryId()
        );

        return ProductMapper.toResponse(product);
    }

    @MutationMapping
    public ProductResponse updateProduct(@Argument("input") UpdateProductInput input) {
        var product = catalogManagementService.updateProduct(
                input.id(),
                input.name(),
                input.description(),
                input.price(),
                input.categoryId()
        );
        return ProductMapper.toResponse(product);
    }

    @MutationMapping
    public ReviewResponse addReview(@Argument("input") AddReviewInput input) {
        var review = catalogManagementService.addReview(
                input.productId(),
                input.rating(),
                input.comment()
        );
        return ReviewMapper.toResponse(review);
    }

    @MutationMapping
    public QualityIssueResponse reportQualityIssue(@Argument("input")ReportQualityIssueInput input) {
        var issue = catalogManagementService.reportQualityIssue(
                input.productId(),
                input.type(),
                input.severity()
        );
        return QualityIssueMapper.toResponse(issue);
    }
}
