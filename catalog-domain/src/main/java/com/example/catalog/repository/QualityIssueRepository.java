package com.example.catalog.repository;

import com.example.catalog.model.QualityIssue;

import java.util.List;

public interface QualityIssueRepository {

    QualityIssue save(QualityIssue issue);

    List<QualityIssue> findByProductId(String productId);
}
