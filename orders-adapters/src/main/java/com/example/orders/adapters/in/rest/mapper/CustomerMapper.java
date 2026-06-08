package com.example.orders.adapters.in.rest.mapper;

import com.example.orders.adapters.in.rest.dto.CustomerResponse;
import com.example.orders.model.Customer;

public class CustomerMapper {

    private CustomerMapper() {}

    public static CustomerResponse toResponse(Customer c) {
        if (c == null) return null;
        return new CustomerResponse(c.id(), c.name(), c.email(), c.phone(), c.createdAt());
    }
}

