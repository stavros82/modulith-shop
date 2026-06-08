package com.example.orders.adapters.out.persistence.repository;

import com.example.orders.adapters.out.persistence.entity.CustomerJpaEntity;
import com.example.orders.adapters.out.persistence.mapper.CustomerPersistenceMapper;
import com.example.orders.model.Customer;
import com.example.orders.repository.CustomerRepository;

import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Lazy;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository jpa;

    public CustomerRepositoryImpl(@Lazy CustomerJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Customer save(Customer customer) {
        CustomerJpaEntity entity = CustomerPersistenceMapper.toEntity(
                customer.id(),
                customer.name(),
                customer.email(),
                customer.phone(),
                customer.createdAt()
        );
        CustomerJpaEntity saved = jpa.save(entity);
        return new com.example.orders.model.Customer(saved.getId(), saved.getName(), saved.getEmail(), saved.getPhone(), saved.getCreatedAt());
    }

    @Override
    public Optional<Customer> findById(String customerId) {
        try {
            UUID id = UUID.fromString(customerId);
            return jpa.findById(id).map(e -> new Customer(e.getId(), e.getName(), e.getEmail(), e.getPhone(), e.getCreatedAt()));
        } catch (IllegalArgumentException ex) {
            return Optional.empty();
        }
    }
}

