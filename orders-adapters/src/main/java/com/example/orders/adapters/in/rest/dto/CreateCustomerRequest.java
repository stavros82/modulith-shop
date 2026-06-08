package com.example.orders.adapters.in.rest.dto;

public record CreateCustomerRequest(
        String name,
        String email,
        String phone
) {}

