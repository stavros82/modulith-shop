package com.example.catalog.adapters.in.graphql.dto.input;

public record ReportQualityIssueInput(
    String productId,
    String type,
    String severity
) {}
