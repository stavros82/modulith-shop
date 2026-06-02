package com.example.catalog.repository;

import com.example.catalog.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Optional<Category> findById(String id);

    List<Category> findAll();

    Category save(Category category);
}
