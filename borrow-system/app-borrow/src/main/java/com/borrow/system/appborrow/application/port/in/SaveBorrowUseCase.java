package com.borrow.system.appitem.appborrow.application.port.in;

import com.borrow.system.modulecore.domain.borrow.Borrow;

public interface SaveBorrowUseCase {
    Borrow saveBorrow(Borrow borrow);
}
