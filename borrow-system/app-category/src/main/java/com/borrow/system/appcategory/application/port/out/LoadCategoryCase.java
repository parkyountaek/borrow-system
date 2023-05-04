package com.borrow.system.appcategory.application.port.out;

import com.borrow.system.modulecore.category.domain.Category;

import java.util.List;

public interface LoadCategoryCase {
    List<Category> getAllByUserId(Long userId);
    Category getByIdAndUserId(Long id, Long userId);
}
