package com.example.catalog.adapters.out.persistence.repository;


import com.example.catalog.adapters.out.persistence.entity.CategoryJpaEntity;
import com.example.catalog.model.Category;

public class CategoryPersistenceMapper {

    public static Category toDomain(CategoryJpaEntity e) {
        if (e == null) return null;

        return new Category(
                e.getId(),
                e.getName(),
                e.getParent() != null ? e.getParent().getId() : null
        );
    }

    public static CategoryJpaEntity toEntity(Category c) {
        if (c == null) return null;

        CategoryJpaEntity e = new CategoryJpaEntity();
        e.setId(c.id());
        e.setName(c.name());
        // parent set in repository impl
        return e;
    }
}
