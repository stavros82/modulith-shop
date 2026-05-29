package com.example.catalog.adapters.graphql.resolver;

import com.example.catalog.adapters.graphql.dto.output.CategoryResponse;
import com.example.catalog.adapters.graphql.dto.output.ProductResponse;
import com.example.catalog.adapters.graphql.dto.output.ReviewResponse;
import com.example.catalog.adapters.graphql.mapper.CategoryMapper;
import com.example.catalog.adapters.graphql.mapper.ReviewMapper;
import com.example.catalog.repository.CategoryRepository;
import com.example.catalog.repository.ReviewRepository;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductFieldResolver {

    private final CategoryRepository categoryRepo;
    private final ReviewRepository reviewRepo;

    public ProductFieldResolver(CategoryRepository categoryRepo, ReviewRepository reviewRepo) {
        this.categoryRepo = categoryRepo;
        this.reviewRepo = reviewRepo;
    }


    @SchemaMapping(typeName = "Product", field = "category")
    public CategoryResponse category(ProductResponse product) {
        if (product.category() == null) return null;
        return categoryRepo.findById(product.category().id())
                .map(CategoryMapper::toResponse)
                .orElse(null);

    }


    @SchemaMapping(typeName = "Product", field = "reviews")
    public List<ReviewResponse> reviews(ProductResponse product) {
        System.out.println(">>> LOADING REVIEWS FOR PRODUCT = " + product.id());




       List<ReviewResponse> responseList=reviewRepo.findByProductId(product.id()).stream()
                .map(ReviewMapper::toResponse)
                .toList();
        System.out.println(">>> REVIEWS FROM DB = " + responseList.size());
        return responseList;
    }
}
