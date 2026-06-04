package com.example.catalog.adapters.out.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class ProductJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @Column(length = 2000)
    private String description;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryJpaEntity category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ReviewJpaEntity> reviews;

    public ProductJpaEntity(String id, String name, BigDecimal price) {
    }

    public ProductJpaEntity() {
    }
// getters/setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryJpaEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryJpaEntity category) {
        this.category = category;
    }

    public List<ReviewJpaEntity> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewJpaEntity> reviews) {
        this.reviews = reviews;
    }
}
