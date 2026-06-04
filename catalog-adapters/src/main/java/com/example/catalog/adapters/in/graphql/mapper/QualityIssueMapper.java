package com.example.catalog.adapters.in.graphql.mapper;


import com.example.catalog.adapters.in.graphql.dto.output.QualityIssueResponse;
import com.example.catalog.model.QualityIssue;

public class QualityIssueMapper {

    public static QualityIssueResponse toResponse(QualityIssue q) {
        return new QualityIssueResponse(
                q.id(),
                q.productId(),
                q.type(),
                q.severity(),
                q.status(),
                q.createdAt());
    }
}
