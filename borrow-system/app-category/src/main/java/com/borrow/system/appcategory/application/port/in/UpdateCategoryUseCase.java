package com.borrow.system.appcategory.application.port.in;

import com.borrow.system.modulecore.category.domain.Category;

public interface UpdateCategoryUseCase {
    Category updateCategory(Long userId, Category category);
}
