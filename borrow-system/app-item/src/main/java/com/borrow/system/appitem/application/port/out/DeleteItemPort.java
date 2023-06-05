package com.borrow.system.appitem.application.port.out;

import com.borrow.system.modulecore.domain.item.Item;

public interface DeleteItemPort {
    void deleteItem(Item item);
}
