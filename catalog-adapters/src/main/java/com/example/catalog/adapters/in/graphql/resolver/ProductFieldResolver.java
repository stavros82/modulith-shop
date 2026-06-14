package com.example.catalog.adapters.in.graphql.resolver;

import com.example.catalog.adapters.in.graphql.dto.output.CategoryResponse;
import com.example.catalog.adapters.in.graphql.dto.output.ProductResponse;
import com.example.catalog.adapters.in.graphql.dto.output.ReviewResponse;
import com.example.catalog.adapters.in.graphql.mapper.CategoryMapper;
import com.example.catalog.adapters.in.graphql.mapper.ReviewMapper;
import com.example.catalog.adapters.in.graphql.service.CatalogQueryService;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductFieldResolver {

    private final CatalogQueryService catalogQueryService;

    public ProductFieldResolver(CatalogQueryService catalogQueryService) {
        this.catalogQueryService = catalogQueryService;
    }


    @SchemaMapping(typeName = "Product", field = "category")
    public CategoryResponse category(ProductResponse product) {
        if (product.category() == null) return null;
        return catalogQueryService.findCategoryById(product.category().id())
                .map(CategoryMapper::toResponse)
                .orElse(null);

    }


    @SchemaMapping(typeName = "Product", field = "reviews")
    public List<ReviewResponse> reviews(ProductResponse product) {
    return catalogQueryService.findReviewsByProductId(product.id()).stream()
                .map(ReviewMapper::toResponse)
                .toList();
    }
}
