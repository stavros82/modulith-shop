package com.example.orders.service;

import com.example.orders.model.Customer;
import com.example.orders.repository.CustomerRepository;

public class CreateCustomerUseCase {

    private final CustomerRepository repository;

    public CreateCustomerUseCase(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer execute(String name, String email, String phone) {
        Customer customer = Customer.create(name, email, phone);
        return repository.save(customer);
    }
}

