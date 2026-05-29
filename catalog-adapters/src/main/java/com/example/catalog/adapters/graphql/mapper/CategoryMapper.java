package com.example.catalog.adapters.graphql.mapper;

import com.example.catalog.adapters.graphql.dto.output.CategoryResponse;
import com.example.catalog.model.Category;


public class CategoryMapper {

    public static CategoryResponse toResponse(Category c) {
        if (c == null) return null;

        return new CategoryResponse(
                c.id(),
                c.name(),
                null // parent resolved by field resolver
        );
    }
}
