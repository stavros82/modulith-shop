package com.example.catalog.adapters.out.event;

import com.example.catalog.event.ProductEventPublisher;
import com.example.shared.ProductCreatedEvent;
import com.example.shared.QualityIssueReportedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringProductEventPublisher implements ProductEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public SpringProductEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publishProductCreated(ProductCreatedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

    @Override
    public void publishQualityIssueReported(QualityIssueReportedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
