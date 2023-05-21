package com.borrow.system.appcategory.adaptor.out;

import com.borrow.system.modulecore.domain.category.Category;

import java.util.List;

public interface LoadUseCase {
    List<Category> getAllByOrganizationId(Long id);
    Category getByIdAndOrganizationId(Long id, Long organizationId);
}
