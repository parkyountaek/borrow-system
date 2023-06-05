package com.borrow.system.appitem.application.port.in;

import com.borrow.system.modulecore.domain.item.Item;

public interface SaveUseCase {
    Item saveItem(Item item);
}
