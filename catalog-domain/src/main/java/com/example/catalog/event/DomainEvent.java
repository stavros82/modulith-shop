package com.example.catalog.event;

import java.time.OffsetDateTime;

public abstract class DomainEvent {

    private final OffsetDateTime occurredAt = OffsetDateTime.now();

    public OffsetDateTime occurredAt() {
        return occurredAt;
    }
}
