package com.example.catalog.adapters.in.graphql.dto.output;

public record CategoryResponse(
    String id,
    String name,
    CategoryResponse parent
) {}
