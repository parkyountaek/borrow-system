package com.borrow.system.appcategory.application.port.in;

import com.borrow.system.modulecore.domain.category.Category;

public interface DeletePort {
    void deleteCategory(Category category);
}
