package com.example.catalog.adapters.in.graphql.dto.input;

public record CreateCategoryInput(
        String name,
        String parentId
) {}
