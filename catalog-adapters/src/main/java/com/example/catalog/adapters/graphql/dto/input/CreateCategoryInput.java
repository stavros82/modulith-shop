package com.example.catalog.adapters.graphql.dto.input;

public record CreateCategoryInput(
        String name,
        String parentId
) {}
