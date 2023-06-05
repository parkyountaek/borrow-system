package com.borrow.system.appitem.application.port.out;

import com.borrow.system.modulecore.domain.item.Item;

import java.util.List;
import java.util.Optional;

public interface LoadUseCase {
    List<Item> getByCategoryIdAndOrganizationId(Long categoryId, Long organizationId);
    Optional<Item> getByIdAndCategoryIdAndOrganizationId(Long id, Long categoryId, Long organizationId);
}
