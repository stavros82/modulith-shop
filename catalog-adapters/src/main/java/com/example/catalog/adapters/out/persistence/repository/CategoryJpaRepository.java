package com.example.catalog.adapters.out.persistence.repository;

import com.example.catalog.adapters.out.persistence.entity.CategoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<CategoryJpaEntity, String> {}
