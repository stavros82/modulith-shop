package com.example.catalog.adapters.out.event;

import com.example.catalog.event.ProductCreatedEvent;
import com.example.catalog.event.ProductEventPublisher;
import com.example.catalog.event.QualityIssueReportedEvent;
import com.example.catalog.model.Product;
import com.example.catalog.model.QualityIssue;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component

public class SpringProductEventPublisher implements ProductEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public SpringProductEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publishProductCreated(Product product) {
        // We map the domain action to the specific Spring ApplicationEvent
        applicationEventPublisher.publishEvent(new ProductCreatedEvent(product.id()));
    }

    @Override
    public void publishQualityIssueReported(QualityIssue savedIssue) {
        applicationEventPublisher.publishEvent(new QualityIssueReportedEvent(
                savedIssue.id(),
                savedIssue.productId(),
                savedIssue.type(),
                savedIssue.severity()
        ));
    }
}