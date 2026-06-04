package com.example.catalog.adapters.out.persistence.mapper;

import com.example.catalog.adapters.out.persistence.entity.QualityIssueJpaEntity;
import com.example.catalog.adapters.out.persistence.entity.ProductJpaEntity;
import com.example.catalog.model.QualityIssue;

public class QualityIssuePersistenceMapper {

    public static QualityIssue toDomain(QualityIssueJpaEntity e) {
        if (e == null) return null;

        return new QualityIssue(
                e.getId(),
                e.getProduct().getId(),
                e.getType(),
                e.getSeverity(),
                e.getStatus(),
                e.getCreatedAt()
        );
    }

    public static QualityIssueJpaEntity toEntity(QualityIssue q) {
        if (q == null) return null;

        QualityIssueJpaEntity e = new QualityIssueJpaEntity();
        e.setId(q.id());
        e.setType(q.type());
        e.setSeverity(q.severity());
        e.setStatus(q.status());
        e.setCreatedAt(q.createdAt());
        // set only product reference (we only need the id for the FK)
        if (q.productId() != null) {
            ProductJpaEntity product = new ProductJpaEntity();
            product.setId(q.productId());
            e.setProduct(product);
        }
        return e;
    }
}
