package com.borrow.system.appcategory.persistence;

import com.borrow.system.appcategory.application.port.in.CreateCategoryCommand;
import com.borrow.system.modulecore.category.domain.Category;

public class CategoryPersistenceAdapter {
    private final CategoryRepository categoryRepository;

    public CategoryPersistenceAdapter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        return this.categoryRepository.save(category);
    }
}
