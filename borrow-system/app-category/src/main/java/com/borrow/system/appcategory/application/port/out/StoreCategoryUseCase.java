package com.borrow.system.appcategory.application.port.out;

import com.borrow.system.modulecore.domain.category.Category;

public interface StoreCategoryUseCase {
    Category storeCategory(Category category);
}
