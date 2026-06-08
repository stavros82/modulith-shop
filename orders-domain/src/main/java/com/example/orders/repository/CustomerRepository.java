package com.example.orders.repository;

import com.example.orders.model.Customer;

import java.util.Optional;

public interface CustomerRepository {

    Customer save(Customer customer);

    Optional<Customer> findById(String customerId);
}

