package com.example.catalog.adapters.persistence.repository;


import com.example.catalog.adapters.persistence.entity.CategoryJpaEntity;
import com.example.catalog.adapters.persistence.entity.ProductJpaEntity;
import com.example.catalog.adapters.persistence.mapper.ProductPersistenceMapper;
import com.example.catalog.exception.CategoryNotFoundException;
import com.example.catalog.model.Product;
import com.example.catalog.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository jpa;
    private final CategoryJpaRepository categoryJpa;

    public ProductRepositoryImpl(ProductJpaRepository jpa, CategoryJpaRepository categoryJpa) {
        this.jpa = jpa;
        this.categoryJpa = categoryJpa;
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
        if (product.categoryId() != null) {
            CategoryJpaEntity category = categoryJpa.findById(product.categoryId())
                    .orElseThrow(() -> new CategoryNotFoundException(product.categoryId()));
            entity.setCategory(category);
        } else {
            entity.setCategory(null);
        }
        ProductJpaEntity saved = jpa.save(entity);
        return ProductPersistenceMapper.toDomain(saved);
    }
}
