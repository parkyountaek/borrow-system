package com.borrow.system.appcategory.application.port.out;

import com.borrow.system.modulecore.category.domain.Category;

public interface StoreCategoryUseCase {
    Category storeCategory(Category category);
}
