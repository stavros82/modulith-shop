package com.example.catalog.adapters.persistence.repository;

import com.example.catalog.adapters.persistence.entity.QualityIssueJpaEntity;
import com.example.catalog.adapters.persistence.mapper.QualityIssuePersistenceMapper;
import com.example.catalog.model.QualityIssue;
import com.example.catalog.repository.QualityIssueRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QualityIssueRepositoryImpl implements QualityIssueRepository {

    private final QualityIssueJpaRepository jpa;

    public QualityIssueRepositoryImpl(QualityIssueJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public QualityIssue save(QualityIssue issue) {
        QualityIssueJpaEntity entity = QualityIssuePersistenceMapper.toEntity(issue);
        QualityIssueJpaEntity saved = jpa.save(entity);
        return QualityIssuePersistenceMapper.toDomain(saved);
    }

    @Override
    public List<QualityIssue> findByProductId(String productId) {
        return jpa.findByProduct_Id(productId).stream()
                .map(QualityIssuePersistenceMapper::toDomain)
                .toList();
    }
}
