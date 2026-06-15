package com.example.catalog.event;


public class QualityIssueReportedEvent extends DomainEvent {

    private  String issueId;
    private  String productId;
    private  String type;
    private  String severity;

    public QualityIssueReportedEvent() {
    }

    public QualityIssueReportedEvent(String issueId, String productId, String type, String severity) {
        this.issueId = issueId;
        this.productId = productId;
        this.type = type;
        this.severity = severity;
    }

    public String issueId() {
        return issueId;
    }

    public String productId() {
        return productId;
    }

    public String type() {
        return type;
    }

    public String severity() {
        return severity;
    }
}
