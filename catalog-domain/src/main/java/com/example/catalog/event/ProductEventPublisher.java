package com.example.catalog.event;

import com.example.catalog.model.Product;
import com.example.catalog.model.QualityIssue;

public interface ProductEventPublisher {
    void publishProductCreated(Product product);

    void publishQualityIssueReported(QualityIssue savedIssue);
}