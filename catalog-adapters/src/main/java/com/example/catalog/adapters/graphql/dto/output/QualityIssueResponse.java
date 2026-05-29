package com.example.catalog.adapters.graphql.dto.output;

import java.time.OffsetDateTime;

public record QualityIssueResponse(
        String id,
        String productId,
        String type,
        String severity,
        String status ,
        OffsetDateTime createdAt
) {}
