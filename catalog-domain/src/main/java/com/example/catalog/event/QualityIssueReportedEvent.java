package com.example.catalog.event;


public class QualityIssueReportedEvent extends DomainEvent {

    private final String issueId;
    private final String productId;

    public QualityIssueReportedEvent(String issueId, String productId) {
        this.issueId = issueId;
        this.productId = productId;
    }

    public String issueId() {
        return issueId;
    }

    public String productId() {
        return productId;
    }
}
