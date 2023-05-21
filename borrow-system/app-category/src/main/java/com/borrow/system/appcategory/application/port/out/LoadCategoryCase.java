package com.borrow.system.appcategory.application.port.out;

import com.borrow.system.modulecore.domain.category.Category;

import java.util.List;

public interface LoadCategoryCase {
    List<Category> getAllByOrganizationId(Long userId);
    Category getByIdAndOrganizationId(Long id, Long userId);
}
