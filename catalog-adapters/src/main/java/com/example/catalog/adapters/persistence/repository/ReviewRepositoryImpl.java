package com.example.catalog.adapters.persistence.repository;

import com.example.catalog.adapters.persistence.entity.ReviewJpaEntity;
import com.example.catalog.adapters.persistence.mapper.ReviewPersistenceMapper;
import com.example.catalog.model.Review;
import com.example.catalog.repository.ReviewRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository {

    private final ReviewJpaRepository jpa;

    public ReviewRepositoryImpl(ReviewJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Review save(Review review) {
        ReviewJpaEntity entity = ReviewPersistenceMapper.toEntity(review);
        ReviewJpaEntity saved = jpa.save(entity);
        return ReviewPersistenceMapper.toDomain(saved);
    }

    @Override
    public List<Review> findByProductId(String productId) {
        return jpa.findByProductId(productId).stream()
                .map(ReviewPersistenceMapper::toDomain)
                .toList();
    }
}
