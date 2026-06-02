package com.example.orders.repository;

import com.example.orders.model.Order;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(String orderId);
}

