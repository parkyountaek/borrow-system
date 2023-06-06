package com.borrow.system.appusermanagement.application.port.out;

import com.borrow.system.modulecore.domain.user.User;

import java.util.Optional;

public interface LoadPort {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
