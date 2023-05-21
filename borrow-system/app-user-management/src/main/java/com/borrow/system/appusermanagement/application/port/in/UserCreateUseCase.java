package com.borrow.system.appusermanagement.application.port.in;

import com.borrow.system.modulecore.domain.user.User;

public interface UserCreateUseCase {
    User createUser(User user);
}
