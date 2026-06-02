package com.example.catalog.adapters.persistence.repository;


import com.example.catalog.adapters.persistence.entity.QualityIssueJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QualityIssueJpaRepository extends JpaRepository<QualityIssueJpaEntity, String> {

    List<QualityIssueJpaEntity> findByProduct_Id(String productId);
}
