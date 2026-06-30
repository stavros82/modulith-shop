package com.example.shared;

import java.time.OffsetDateTime;

public abstract class DomainEvent {
    private final OffsetDateTime occurredAt = OffsetDateTime.now();

    public OffsetDateTime occurredAt() {
        return occurredAt;
    }
}
