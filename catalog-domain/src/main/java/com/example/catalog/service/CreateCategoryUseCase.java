package com.example.catalog.service;

import com.example.catalog.exception.CategoryNotFoundException;
import com.example.catalog.model.Category;
import com.example.catalog.repository.CategoryRepository;

import java.util.UUID;

public class CreateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public CreateCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category execute(String name, String parentId) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Category name is required");
        }
        if (parentId != null && categoryRepository.findById(parentId).isEmpty()) {
            throw new CategoryNotFoundException(parentId);
        }

        Category category = new Category(
                UUID.randomUUID().toString(),
                name.trim(),
                parentId
        );
        return categoryRepository.save(category);
    }
}
