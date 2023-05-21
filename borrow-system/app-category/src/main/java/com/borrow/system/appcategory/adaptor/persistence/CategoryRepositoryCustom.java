package com.borrow.system.appcategory.adaptor.persistence;

import com.borrow.system.modulecore.domain.category.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryCustom {
    List<Category> findAllByOrganizationId(Long organizationId);

    Optional<Category> findByIdAndOrganizationId(Long id, Long organizationId);
}
