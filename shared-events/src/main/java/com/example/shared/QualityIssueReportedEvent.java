package com.example.shared;

public class QualityIssueReportedEvent extends DomainEvent {
    private final String issueId;
    private final String productId;
    private final String type;
    private final String severity;

    public QualityIssueReportedEvent(String issueId, String productId, String type, String severity) {
        this.issueId = issueId;
        this.productId = productId;
        this.type = type;
        this.severity = severity;
    }

    public String issueId() { return issueId; }
    public String productId() { return productId; }
    public String type() { return type; }
    public String severity() { return severity; }
}
