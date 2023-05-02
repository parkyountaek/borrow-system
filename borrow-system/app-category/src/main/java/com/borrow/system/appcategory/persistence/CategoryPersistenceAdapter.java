package com.borrow.system.appcategory.persistence;

public class CategoryPersistenceAdapter {
    private final CategoryRepository categoryRepository;

    public CategoryPersistenceAdapter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
