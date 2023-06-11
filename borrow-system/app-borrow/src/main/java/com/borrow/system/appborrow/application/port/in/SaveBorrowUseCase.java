package com.borrow.system.appborrow.application.port.in;

import com.borrow.system.modulecore.domain.borrow.Borrow;

public interface SaveBorrowUseCase {
    Borrow saveBorrow(Borrow borrow);
}
