package com.borrow.system.appcategory.application.port.out;

import com.borrow.system.modulecore.domain.category.Category;

import java.util.List;
import java.util.Optional;

public interface LoadPort {
    List<Category> getAllByOrganizationId(Long id);
    Optional<Category> findByIdAndOrganizationId(Long id, Long organizationId);
}
