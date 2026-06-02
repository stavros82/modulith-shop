package com.example.orders.adapters.persistence.repository;

import com.example.orders.adapters.persistence.entity.OrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderJpaEntity, String> {
}

