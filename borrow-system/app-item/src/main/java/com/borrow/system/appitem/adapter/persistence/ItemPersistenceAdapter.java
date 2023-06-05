package com.borrow.system.appitem.adapter.persistence;

import com.borrow.system.appitem.application.port.out.DeleteItemPort;
import com.borrow.system.appitem.application.port.out.SaveItemPort;
import com.borrow.system.appitem.application.port.out.LoadItemPort;
import com.borrow.system.modulecore.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ItemPersistenceAdapter implements SaveItemPort, LoadItemPort, DeleteItemPort {
    private final ItemRepository itemRepository;

    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public List<Item> findByCategoryIdAndOrganizationId(Long categoryId, Long organizationId) {
        return itemRepository.findByCategoryIdAndOrganizationId(categoryId, organizationId);
    }

    @Override
    public Optional<Item> findByIdAndCategoryIdAndOrganizationId(Long id, Long categoryId, Long organizationId) {
        return itemRepository.findByIdAndCategoryIdAndOrganizationId(id, categoryId, organizationId);
    }

    @Override
    public void deleteItem(Item item) {
        itemRepository.delete(item);
    }
}
