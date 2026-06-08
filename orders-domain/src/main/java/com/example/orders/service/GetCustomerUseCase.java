package com.example.orders.service;

import com.example.orders.model.Customer;
import com.example.orders.repository.CustomerRepository;

public class GetCustomerUseCase {

    private final CustomerRepository repository;

    public GetCustomerUseCase(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer execute(String customerId) {
        return repository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + customerId));
    }
}

