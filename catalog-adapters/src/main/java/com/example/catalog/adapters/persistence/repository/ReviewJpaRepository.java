package com.example.catalog.adapters.persistence.repository;

import com.example.catalog.adapters.persistence.entity.ReviewJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewJpaRepository extends JpaRepository<ReviewJpaEntity, String> {

    List<ReviewJpaEntity> findByProductId(String productId);
}
