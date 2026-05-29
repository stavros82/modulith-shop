package com.example.catalog.adapters.persistence.repository;

import com.example.catalog.model.Category;
import com.example.catalog.repository.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryJpaRepository jpa;

    public CategoryRepositoryImpl(CategoryJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Optional<Category> findById(String id) {
        return jpa.findById(id).map(CategoryPersistenceMapper::toDomain);
    }

    @Override
    public List<Category> findAll() {
        return jpa.findAll().stream()
                .map(CategoryPersistenceMapper::toDomain)
                .toList();
    }
}
