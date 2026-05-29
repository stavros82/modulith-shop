package com.example.catalog.adapters.graphql.mapper;

import com.example.catalog.adapters.graphql.dto.output.ProductResponse;
import com.example.catalog.model.Product;




public class ProductMapper {

    public static ProductResponse toResponse(Product p) {
        return new ProductResponse(
                p.id(),
                p.name(),
                p.description(),
                p.price(),
                null, // category resolved by field resolver

                p.averageRating()
        );
    }
}
