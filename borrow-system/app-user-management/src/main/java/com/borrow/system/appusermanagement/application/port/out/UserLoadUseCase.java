package com.borrow.system.appusermanagement.application.port.out;

import com.borrow.system.modulecore.domain.user.User;

public interface UserLoadUseCase {
    User getUserByEmail(String email);

    User getUserById(Long id);
}
