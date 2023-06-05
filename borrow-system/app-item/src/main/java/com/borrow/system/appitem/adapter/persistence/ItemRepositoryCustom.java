package com.borrow.system.appitem.adapter.persistence;

import com.borrow.system.modulecore.domain.item.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepositoryCustom {
    List<Item> findByCategoryIdAndOrganizationId(Long categoryId, Long organizationId);
    Optional<Item> findByIdAndCategoryIdAndOrganizationId(Long id, Long categoryId, Long organizationId);
}
