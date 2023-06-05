package com.borrow.system.appitem.application.port.out;

import com.borrow.system.modulecore.domain.item.Item;

import java.util.List;
import java.util.Optional;

public interface LoadItemPort {
    List<Item> findByCategoryIdAndOrganizationId(Long categoryId, Long organizationId);
    Optional<Item> findByIdAndCategoryIdAndOrganizationId(Long id, Long categoryId, Long organizationId);
}
