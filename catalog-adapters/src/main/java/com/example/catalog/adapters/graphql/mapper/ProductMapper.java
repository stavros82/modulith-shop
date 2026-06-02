package com.example.catalog.adapters.graphql.mapper;

import com.example.catalog.adapters.graphql.dto.output.CategoryResponse;
import com.example.catalog.adapters.graphql.dto.output.ProductResponse;
import com.example.catalog.model.Product;




public class ProductMapper {

    public static ProductResponse toResponse(Product p) {
        return new ProductResponse(
                p.id(),
                p.name(),
                p.description(),
                p.price(),
                categoryStub(p),

                p.averageRating()
        );
    }

    private static CategoryResponse categoryStub(Product p) {
        if (p.categoryId() == null) {
            return null;
        }
        return new CategoryResponse(p.categoryId(), null, null);
    }
}
