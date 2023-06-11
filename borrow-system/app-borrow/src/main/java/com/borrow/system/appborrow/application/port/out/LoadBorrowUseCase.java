package com.borrow.system.appborrow.application.port.out;

import com.borrow.system.modulecore.domain.borrow.Borrow;

public interface LoadBorrowUseCase {
    Borrow getByUserId(Long userId);
    Borrow getByItemId(Long itemId);
    Borrow getById(Long id);
}
