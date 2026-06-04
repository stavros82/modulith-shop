package com.example.catalog.adapters.out.persistence.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "quality_issues")
public class QualityIssueJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String type;
    private String severity;
    private String status;

    private OffsetDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductJpaEntity product;

    // getters/setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ProductJpaEntity getProduct() {
        return product;
    }

    public void setProduct(ProductJpaEntity product) {
        this.product = product;
    }
}
