package com.example.catalog.adapters.graphql.dto.input;

public record ReportQualityIssueInput(
    String productId,
    String type,
    String severity
) {}
