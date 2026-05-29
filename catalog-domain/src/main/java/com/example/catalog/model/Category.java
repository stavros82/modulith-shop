package com.example.catalog.model;

public class Category {

    private final String id;
    private final String name;
    private final String parentId;

    public Category(String id, String name, String parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public String id() { return id; }
    public String name() { return name; }
    public String parentId() { return parentId; }
}
