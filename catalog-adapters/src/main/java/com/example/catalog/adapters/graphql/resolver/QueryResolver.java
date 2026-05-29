package com.example.catalog.adapters.graphql.resolver;

import com.example.catalog.adapters.graphql.dto.output.CategoryResponse;
import com.example.catalog.adapters.graphql.dto.output.ProductResponse;
import com.example.catalog.adapters.graphql.mapper.CategoryMapper;
import com.example.catalog.adapters.graphql.mapper.ProductMapper;
import com.example.catalog.repository.CategoryRepository;
import com.example.catalog.service.GetProductUseCase;
import com.example.catalog.service.ListProductsUseCase;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class QueryResolver {

    private final GetProductUseCase getProduct;
    private final ListProductsUseCase listProducts;
    private final CategoryRepository categoryRepo;

    public QueryResolver(
            GetProductUseCase getProduct,
            ListProductsUseCase listProducts,
            CategoryRepository categoryRepo
    ) {
        this.getProduct = getProduct;
        this.listProducts = listProducts;
        this.categoryRepo = categoryRepo;
    }

    @QueryMapping
    public ProductResponse product(@Argument("id") String id) {
        return ProductMapper.toResponse(getProduct.execute(id));
    }

    @QueryMapping
    public List<ProductResponse> products() {
        return listProducts.execute().stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    @QueryMapping
    public List<CategoryResponse> categories() {
        return categoryRepo.findAll().stream()
                .map(CategoryMapper::toResponse)
                .toList();
    }
}
