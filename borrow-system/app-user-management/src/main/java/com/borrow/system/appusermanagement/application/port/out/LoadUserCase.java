package com.borrow.system.appusermanagement.application.port.out;

import com.borrow.system.modulecore.user.domain.User;

public interface LoadUser {
    User getUser(Long id);
}
