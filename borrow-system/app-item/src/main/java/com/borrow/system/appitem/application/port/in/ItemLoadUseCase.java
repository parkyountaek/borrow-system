package com.borrow.system.appitem.application.port.in;

import com.borrow.system.modulecore.domain.item.Item;

import java.util.List;

public interface ItemLoadUseCase {
    List<Item> getByCategoryIdAndOrganizationId(Long categoryId, Long organizationId);
    Item getByIdAndCategoryIdAndOrganizationId(Long id, Long categoryId, Long organizationId);
}
