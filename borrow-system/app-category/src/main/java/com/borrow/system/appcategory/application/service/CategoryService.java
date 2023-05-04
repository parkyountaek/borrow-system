package com.borrow.system.appcategory.application.service;

import com.borrow.system.appcategory.application.port.in.CreateCategoryCommand;
import com.borrow.system.appcategory.application.port.in.CreateUseCase;
import com.borrow.system.appcategory.application.port.out.LoadCategoryCase;
import com.borrow.system.appcategory.persistence.CategoryPersistenceAdapter;
import com.borrow.system.modulecore.category.domain.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements CreateUseCase, LoadCategoryCase {
    private final CategoryPersistenceAdapter categoryPersistenceAdapter;

    public CategoryService(CategoryPersistenceAdapter categoryPersistenceAdapter) {
        this.categoryPersistenceAdapter = categoryPersistenceAdapter;
    }

    @Override
    public Category createCategory(CreateCategoryCommand createCategoryCommand) {
        Category category = createCategoryCommand.toEntity();
        return this.categoryPersistenceAdapter.createCategory(category);
    }
}
