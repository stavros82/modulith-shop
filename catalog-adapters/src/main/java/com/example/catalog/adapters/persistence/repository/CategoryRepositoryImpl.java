package com.example.catalog.adapters.persistence.repository;

import com.example.catalog.adapters.persistence.entity.CategoryJpaEntity;
import com.example.catalog.exception.CategoryNotFoundException;
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

    @Override
    public Category save(Category category) {
        CategoryJpaEntity entity = CategoryPersistenceMapper.toEntity(category);
        if (category.parentId() != null) {
            CategoryJpaEntity parent = jpa.findById(category.parentId())
                    .orElseThrow(() -> new CategoryNotFoundException(category.parentId()));
            entity.setParent(parent);
        }
        return CategoryPersistenceMapper.toDomain(jpa.save(entity));
    }
}
