package com.borrow.system.appitem.application.port.in;

import com.borrow.system.modulecore.domain.item.Item;
import com.borrow.system.modulecore.domain.user.User;

public interface ItemBorrowUseCase {
    Item borrowItem(Item item, User user);
}
