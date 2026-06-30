package com.example.orders.adapters.events;

import com.example.orders.event.OrderEventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisherImpl implements OrderEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public OrderEventPublisherImpl(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void publishEvent(Object event) {
        // In a real application, this would publish to a message broker or event bus.
        this.eventPublisher.publishEvent(event);
   }
}
