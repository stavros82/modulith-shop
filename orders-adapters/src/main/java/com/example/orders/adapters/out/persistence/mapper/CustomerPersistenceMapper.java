package com.example.orders.adapters.out.persistence.mapper;

import com.example.orders.adapters.out.persistence.entity.CustomerJpaEntity;

import java.time.Instant;
import java.util.UUID;

public class CustomerPersistenceMapper {

    private CustomerPersistenceMapper() {}

    public static CustomerJpaEntity toEntity(UUID id,
                                              String name,
                                              String email,
                                              String phone,
                                              Instant createdAt) {
        return new CustomerJpaEntity(id, name, email, phone, createdAt);
    }
}
