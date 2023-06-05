package com.borrow.system.appcategory.application.service;

import com.borrow.system.appcategory.application.port.in.DeleteCategoryUseCase;
import com.borrow.system.appcategory.application.port.in.DeletePort;
import com.borrow.system.appcategory.application.port.in.SavePort;
import com.borrow.system.appcategory.application.port.in.UpdateCategoryUseCase;
import com.borrow.system.appcategory.application.port.out.LoadCategoryCase;
import com.borrow.system.appcategory.application.port.out.LoadPort;
import com.borrow.system.appcategory.application.port.out.StoreCategoryUseCase;
import com.borrow.system.modulecommon.exception.BusinessLogicException;
import com.borrow.system.modulecommon.exception.ExceptionCode;
import com.borrow.system.modulecore.domain.category.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService implements StoreCategoryUseCase, LoadCategoryCase, UpdateCategoryUseCase, DeleteCategoryUseCase {
    private final LoadPort loadPort;
    private final DeletePort deletePort;
    private final SavePort savePort;

    @Override
    public Category storeCategory(Category category) {
        return savePort.saveCategory(category);
    }

    @Override
    public List<Category> getAllByOrganizationId(Long organizationId) {
        return loadPort.getAllByOrganizationId(organizationId);
    }

    @Override
    public Category getByIdAndOrganizationId(Long id, Long organizationId) {
        return loadPort.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.CATEGORY_NOT_FOUND));
    }

    @Override
    public Category updateCategory(Category category, Long organizationId) {
        Category findCategory = getByIdAndOrganizationId(category.getId(), organizationId);
        findCategory.update(category);
        return storeCategory(findCategory);
    }

    @Override
    public void deleteCategory(Long id, Long organizationId) {
        Category findCategory = getByIdAndOrganizationId(id, organizationId);
        deletePort.deleteCategory(findCategory);
    }
}