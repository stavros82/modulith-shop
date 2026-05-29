package com.example.catalog.model;

import java.time.OffsetDateTime;

public class QualityIssue {

    private final String id;
    private final String productId;
    private final String type;
    private final String severity;
    private String status;
    private final OffsetDateTime createdAt;

    public QualityIssue(String id, String productId, String type, String severity, String status, OffsetDateTime createdAt) {
        this.id = id;
        this.productId = productId;
        this.type = type;
        this.severity = severity;
        this.status = status;
        this.createdAt = createdAt;
    }

    public void resolve() {
        this.status = "RESOLVED";
    }

    public String id() { return id; }
    public String productId() { return productId; }
    public String type() { return type; }
    public String severity() { return severity; }
    public String status() { return status; }
    public OffsetDateTime createdAt() { return createdAt; }
}
