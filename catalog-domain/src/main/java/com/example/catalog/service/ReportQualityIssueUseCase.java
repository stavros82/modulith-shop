package com.example.catalog.service;



import com.example.catalog.event.ProductEventPublisher;
import com.example.catalog.model.QualityIssue;
import com.example.catalog.repository.QualityIssueRepository;

import java.time.OffsetDateTime;
import java.util.UUID;

public class ReportQualityIssueUseCase {

    private final QualityIssueRepository qualityIssueRepository;
    private final ProductEventPublisher eventPublisher;

    public ReportQualityIssueUseCase(QualityIssueRepository qualityIssueRepository, ProductEventPublisher eventPublisher) {
        this.qualityIssueRepository = qualityIssueRepository;
        this.eventPublisher = eventPublisher;
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

        QualityIssue savedIssue = qualityIssueRepository.save(issue);
        eventPublisher.publishQualityIssueReported(new com.example.shared.QualityIssueReportedEvent(
                savedIssue.id(), savedIssue.productId(), savedIssue.type(), savedIssue.severity()));
        return savedIssue;
    }
}
