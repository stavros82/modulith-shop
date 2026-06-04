package com.example.catalog.adapters.out.persistence.repository;

import com.example.catalog.adapters.out.persistence.entity.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, String> {
}
