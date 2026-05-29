package com.example.catalog.adapters.persistence.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CategoryJpaEntity parent;

    @OneToMany(mappedBy = "parent")
    private List<CategoryJpaEntity> children;

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

    public CategoryJpaEntity getParent() {
        return parent;
    }

    public void setParent(CategoryJpaEntity parent) {
        this.parent = parent;
    }

    public List<CategoryJpaEntity> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryJpaEntity> children) {
        this.children = children;
    }
// getters/setters
}
