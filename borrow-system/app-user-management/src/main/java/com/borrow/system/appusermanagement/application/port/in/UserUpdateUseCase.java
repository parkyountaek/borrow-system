package com.borrow.system.appusermanagement.application.port.in;

import com.borrow.system.modulecore.user.domain.User;

public interface UserUpdateUseCase {
    User updateUser(User user);
}
