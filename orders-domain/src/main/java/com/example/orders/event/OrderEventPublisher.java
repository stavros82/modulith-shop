package com.example.orders.event;

public interface OrderEventPublisher {
    void publishEvent(Object event);
}
