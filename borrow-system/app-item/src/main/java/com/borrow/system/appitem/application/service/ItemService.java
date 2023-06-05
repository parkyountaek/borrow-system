package com.borrow.system.appitem.application.service;

import com.borrow.system.appitem.application.port.in.*;
import com.borrow.system.appitem.application.port.out.DeleteItemPort;
import com.borrow.system.appitem.application.port.out.SaveItemPort;
import com.borrow.system.appitem.application.port.out.LoadItemPort;
import com.borrow.system.modulecommon.exception.BusinessLogicException;
import com.borrow.system.modulecommon.exception.ExceptionCode;
import com.borrow.system.modulecore.domain.item.Item;
import com.borrow.system.modulecore.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService implements ItemEnrollUseCase, ItemUpdateUseCase, ItemLoadUseCase, ItemDeleteUseCase, ItemBorrowUseCase {
    private final SaveItemPort saveItemPort;
    private final LoadItemPort loadItemPort;
    private final DeleteItemPort deleteItemPort;

    @Override
    public void deleteItem(Item item) {
        Item findItem = getByIdAndCategoryIdAndOrganizationId(item.getId(), item.getCategory().getId(), item.getCategory().getOrganization().getId());
        deleteItemPort.deleteItem(findItem);
    }

    @Override
    public Item enrollItem(Item item) {
        return saveItemPort.saveItem(item);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> getByCategoryIdAndOrganizationId(Long categoryId, Long organizationId) {
        return loadItemPort.findByCategoryIdAndOrganizationId(categoryId, organizationId);
    }

    @Override
    @Transactional(readOnly = true)
    public Item getByIdAndCategoryIdAndOrganizationId(Long id, Long categoryId, Long organizationId) {
        return loadItemPort.findByIdAndCategoryIdAndOrganizationId(id, categoryId, organizationId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ITEM_NOT_FOUND));
    }

    @Override
    public Item updateItem(Item item) {
        Item findItem = getByIdAndCategoryIdAndOrganizationId(item.getId(), item.getCategory().getId(), item.getCategory().getOrganization().getId());
        findItem.updateProperty(item);
        return saveItemPort.saveItem(findItem);
    }

    @Override
    public Item borrowItem(Item item, User user) {
        Item findItem = getByIdAndCategoryIdAndOrganizationId(item.getId(), item.getCategory().getId(), item.getCategory().getOrganization().getId());
        if(Boolean.TRUE.equals(item.getIsBorrow())) {
            throw new BusinessLogicException(ExceptionCode.ITEM_ALREADY_BORROW);
        } else if(!Objects.equals(findItem.getCategory().getOrganization().getId(), user.getOrganization().getId())) {
            throw new BusinessLogicException(ExceptionCode.NO_PERMISSION);
        }
        findItem.borrow();
        return saveItemPort.saveItem(findItem);
    }
}
