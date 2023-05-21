package com.borrow.system.appcategory.application.service;

import com.borrow.system.appcategory.adaptor.persistence.CategoryPersistenceAdapter;
import com.borrow.system.appcategory.application.port.in.DeleteCategoryUseCase;
import com.borrow.system.appcategory.application.port.in.UpdateCategoryUseCase;
import com.borrow.system.appcategory.application.port.out.LoadCategoryCase;
import com.borrow.system.appcategory.application.port.out.StoreCategoryUseCase;
import com.borrow.system.modulecore.domain.category.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService implements StoreCategoryUseCase, LoadCategoryCase, UpdateCategoryUseCase, DeleteCategoryUseCase {
    private final CategoryPersistenceAdapter categoryPersistenceAdapter;

    @Override
    public Category storeCategory(Category category) {
        return this.categoryPersistenceAdapter.saveCategory(category);
    }

    @Override
    public List<Category> getAllByOrganizationId(Long organizationId) {
        return this.categoryPersistenceAdapter.getAllByOrganizationId(organizationId);
    }

    @Override
    public Category getByIdAndOrganizationId(Long id, Long organizationId) {
        return this.categoryPersistenceAdapter.getByIdAndOrganizationId(id, organizationId);
    }

    @Override
    public Category updateCategory(Category category, Long organizationId) {
        Category findCategory = this.getByIdAndOrganizationId(category.getId(), organizationId);
        findCategory.update(category);
        return this.storeCategory(findCategory);
    }

    @Override
    public void deleteCategory(Long id, Long organizationId) {
        Category findCategory = this.getByIdAndOrganizationId(id, organizationId);
        this.categoryPersistenceAdapter.deleteCategory(findCategory);
    }
}