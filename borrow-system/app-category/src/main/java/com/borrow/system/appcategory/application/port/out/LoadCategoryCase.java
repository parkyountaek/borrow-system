package com.borrow.system.appcategory.application.port.out;

import com.borrow.system.modulecore.domain.category.Category;

import java.util.List;

public interface LoadCategoryCase {
    List<Category> getAllByUserId(Long userId);
    Category getByIdAndUserId(Long id, Long userId);
}
