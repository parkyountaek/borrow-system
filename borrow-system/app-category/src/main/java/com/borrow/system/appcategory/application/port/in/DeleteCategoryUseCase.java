package com.borrow.system.appcategory.application.port.in;

public interface DeleteCategoryUseCase {
    void deleteCategory(Long id, Long organizationId);
}
