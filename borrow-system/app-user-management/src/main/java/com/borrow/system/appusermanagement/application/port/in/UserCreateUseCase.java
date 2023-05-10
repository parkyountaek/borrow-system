package com.borrow.system.appusermanagement.application.port.in;

import com.borrow.system.modulecore.user.domain.User;

public interface UserCreateUseCase {
    User createUser(User user);
}
