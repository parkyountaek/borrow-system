package com.borrow.system.appcategory.application.service;

import com.borrow.system.appcategory.application.port.in.DeleteCategoryUseCase;
import com.borrow.system.appcategory.application.port.out.StoreCategoryUseCase;
import com.borrow.system.appcategory.application.port.in.UpdateCategoryUseCase;
import com.borrow.system.appcategory.application.port.out.LoadCategoryCase;
import com.borrow.system.appcategory.adaptor.persistence.CategoryPersistenceAdapter;
import com.borrow.system.modulecommon.util.CustomBeanUtils;
import com.borrow.system.modulecore.domain.category.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService implements StoreCategoryUseCase, LoadCategoryCase, UpdateCategoryUseCase, DeleteCategoryUseCase {
    private final CategoryPersistenceAdapter categoryPersistenceAdapter;
    private final CustomBeanUtils<Category> beanUtils;

    public CategoryService(CategoryPersistenceAdapter categoryPersistenceAdapter, CustomBeanUtils<Category> beanUtils) {
        this.categoryPersistenceAdapter = categoryPersistenceAdapter;
        this.beanUtils = beanUtils;
    }

    @Override
    public Category storeCategory(Category category) {
        return this.categoryPersistenceAdapter.saveCategory(category);
    }

    @Override
    public List<Category> getAllByUserId(Long userId) {
        return null;
    }

    @Override
    public Category getByIdAndUserId(Long id, Long userId) {
        return null;
//        Optional<Category> optionalCategory = this.categoryPersistenceAdapter.findByIdAndUserId(id, userId);
//        return optionalCategory.orElseThrow(() -> new BusinessLogicException(ExceptionCode.CATEGORY_NOT_FOUND));
    }

    @Override
    public Category updateCategory(Long userId, Category category) {
        Category findCategory = this.getByIdAndUserId(category.getId(), userId);
        Category updateCategory = this.beanUtils.copyNonNullProperties(category, findCategory);
        return this.storeCategory(updateCategory);
    }

    @Override
    public void deleteCategory(Long id, Long userId) {
        Category findCategory = this.getByIdAndUserId(id, userId);
        this.categoryPersistenceAdapter.deleteCategory(findCategory);
    }
}