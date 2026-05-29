package com.example.catalog.adapters.persistence.repository;

import com.example.catalog.adapters.persistence.entity.CategoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<CategoryJpaEntity, String> {}
