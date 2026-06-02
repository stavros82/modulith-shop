package com.example.inventory.adapters.persistence.repository;

import com.example.inventory.adapters.persistence.entity.InventoryProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryProductJpaRepository extends JpaRepository<InventoryProductJpaEntity, String> {
}

