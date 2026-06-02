package com.example.orders.adapters.persistence.repository;

import com.example.orders.adapters.persistence.entity.OrderJpaEntity;
import com.example.orders.adapters.persistence.mapper.OrderPersistenceMapper;
import com.example.orders.model.Order;
import com.example.orders.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository jpa;

    public OrderRepositoryImpl(OrderJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Order save(Order order) {
        OrderJpaEntity saved = jpa.save(OrderPersistenceMapper.toEntity(order));
        return OrderPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Order> findById(String orderId) {
        return jpa.findById(orderId).map(OrderPersistenceMapper::toDomain);
    }
}

