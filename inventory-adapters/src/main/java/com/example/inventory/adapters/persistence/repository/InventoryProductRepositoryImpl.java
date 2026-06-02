package com.example.inventory.adapters.persistence.repository;

import com.example.inventory.adapters.persistence.entity.InventoryProductJpaEntity;
import com.example.inventory.adapters.persistence.mapper.InventoryProductPersistenceMapper;
import com.example.inventory.model.InventoryProduct;
import com.example.inventory.repository.InventoryProductRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InventoryProductRepositoryImpl implements InventoryProductRepository {

    private final InventoryProductJpaRepository jpa;

    public InventoryProductRepositoryImpl(InventoryProductJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Optional<InventoryProduct> findByProductId(String productId) {
        return jpa.findById(productId).map(InventoryProductPersistenceMapper::toDomain);
    }

    @Override
    public InventoryProduct save(InventoryProduct product) {
        InventoryProductJpaEntity entity = InventoryProductPersistenceMapper.toEntity(product);
        InventoryProductJpaEntity saved = jpa.save(entity);
        return InventoryProductPersistenceMapper.toDomain(saved);
    }
}

