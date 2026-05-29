package com.example.catalog.adapters.graphql.dto.output;

public record CategoryResponse(
    String id,
    String name,
    CategoryResponse parent
) {}
