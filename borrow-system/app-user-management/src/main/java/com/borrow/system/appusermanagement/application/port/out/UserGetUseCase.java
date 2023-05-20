package com.borrow.system.appusermanagement.application.port.out;

import com.borrow.system.modulecore.user.domain.User;

public interface UserGetUseCase {
    User getUserByEmail(String email);

    User getUserById(Long id);
}
