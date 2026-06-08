package com.example.orders.model;

import java.time.Instant;
import java.util.UUID;

public class Customer {

    private final UUID id;
    private String name;
    private String email;
    private String phone;
    private final Instant createdAt;

    public Customer(UUID id,
                    String name,
                    String email,
                    String phone,
                    Instant createdAt) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }

        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.createdAt = createdAt;
    }

    public static Customer create(String name, String email, String phone) {
        return new Customer(
                UUID.randomUUID(),
                name,
                email,
                phone,
                Instant.now()
        );
    }

    public void updateContactInfo(String newEmail, String newPhone) {
        this.email = newEmail;
        this.phone = newPhone;
    }

    public UUID id() { return id; }
    public String name() { return name; }
    public String email() { return email; }
    public String phone() { return phone; }
    public Instant createdAt() { return createdAt; }
}
