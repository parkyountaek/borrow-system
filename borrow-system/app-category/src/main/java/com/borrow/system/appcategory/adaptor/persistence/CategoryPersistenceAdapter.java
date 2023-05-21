package com.borrow.system.appcategory.adaptor.persistence;

import com.borrow.system.appcategory.adaptor.in.DeleteUseCase;
import com.borrow.system.appcategory.adaptor.in.SaveUseCase;
import com.borrow.system.appcategory.adaptor.out.LoadUseCase;
import com.borrow.system.modulecommon.exception.BusinessLogicException;
import com.borrow.system.modulecommon.exception.ExceptionCode;
import com.borrow.system.modulecore.domain.category.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryPersistenceAdapter implements SaveUseCase, LoadUseCase, DeleteUseCase {
    private final CategoryRepository categoryRepository;

    public CategoryPersistenceAdapter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category saveCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Category category) {
        this.categoryRepository.delete(category);
    }

    @Override
    public List<Category> getAllByOrganizationId(Long organizationId) {
        return categoryRepository.findAllByOrganizationId(organizationId);
    }

    @Override
    public Category getByIdAndOrganizationId(Long id, Long organizationId) {
        return categoryRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.CATEGORY_NOT_FOUND));
    }
}