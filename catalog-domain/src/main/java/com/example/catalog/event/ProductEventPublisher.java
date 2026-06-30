package com.example.catalog.event;


import com.example.shared.ProductCreatedEvent;
import com.example.shared.QualityIssueReportedEvent;

public interface ProductEventPublisher {
    void publishProductCreated(ProductCreatedEvent event);
    void publishQualityIssueReported(QualityIssueReportedEvent event);
}
