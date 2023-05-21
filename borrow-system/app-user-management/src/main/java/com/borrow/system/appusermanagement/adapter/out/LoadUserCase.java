package com.borrow.system.appusermanagement.adapter.out;

import com.borrow.system.modulecore.domain.user.User;

public interface LoadUserCase {
    User getUserById(Long id);
    User getUserByEmail(String email);
    void existUserByEmail(String email);
}
