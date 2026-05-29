package com.example.catalog.service;



import com.example.catalog.model.QualityIssue;
import com.example.catalog.repository.QualityIssueRepository;

import java.time.OffsetDateTime;
import java.util.UUID;

public class ReportQualityIssueUseCase {

    private final QualityIssueRepository qualityIssueRepository;

    public ReportQualityIssueUseCase(QualityIssueRepository qualityIssueRepository) {
        this.qualityIssueRepository = qualityIssueRepository;
    }

    public QualityIssue execute(String productId, String type, String severity) {
        QualityIssue issue = new QualityIssue(
                UUID.randomUUID().toString(),
                productId,
                type,
                severity,
                "OPEN",
                OffsetDateTime.now()
        );

        return qualityIssueRepository.save(issue);
    }
}
