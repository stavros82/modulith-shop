package com.example.catalog.adapters.persistence.repository;


import com.example.catalog.adapters.persistence.entity.ProductJpaEntity;
import com.example.catalog.adapters.persistence.mapper.ProductPersistenceMapper;
import com.example.catalog.model.Product;
import com.example.catalog.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository jpa;

    public ProductRepositoryImpl(ProductJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Optional<Product> findById(String id) {
        return jpa.findById(id).map(ProductPersistenceMapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return jpa.findAll().stream()
                .map(ProductPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Product save(Product product) {
        ProductJpaEntity entity = ProductPersistenceMapper.toEntity(product);
        System.out.println("product  = " + product);
        ProductJpaEntity saved = jpa.save(entity);
        System.out.println("entity  = " + entity);
        return ProductPersistenceMapper.toDomain(saved);
    }
}
