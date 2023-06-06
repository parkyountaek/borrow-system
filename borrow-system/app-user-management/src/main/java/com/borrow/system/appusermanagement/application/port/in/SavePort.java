package com.borrow.system.appusermanagement.application.port.in;

import com.borrow.system.modulecore.domain.user.User;

public interface SavePort {
    User saveUser(User user);
}
