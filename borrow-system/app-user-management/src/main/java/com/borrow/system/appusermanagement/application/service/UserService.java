package com.borrow.system.appusermanagement.application.service;

import java.util.Optional;

import com.borrow.system.appusermanagement.application.port.in.AdminCreateUseCase;
import com.borrow.system.modulecommon.exception.BusinessLogicException;
import com.borrow.system.modulecommon.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import com.borrow.system.appusermanagement.adapter.persistence.UserPersistenceAdapter;
import com.borrow.system.appusermanagement.application.port.in.UserCreateUseCase;
import com.borrow.system.modulecore.user.domain.User;

@Service
public class UserService implements UserCreateUseCase, AdminCreateUseCase {
    private final UserPersistenceAdapter userPersistenceAdapter;

    public UserService(UserPersistenceAdapter userPersistenceAdapter) {
        this.userPersistenceAdapter = userPersistenceAdapter;
    }

    public User findVerifiedUser(String email) {
        return this.userPersistenceAdapter.getUserByEmail(email);
    }

    public void existUser(String email) {
        this.userPersistenceAdapter.getUserByEmail(email);
    }

    @Override
    public User createAdmin(User user) {
        existUser(user.getEmail());
        return this.userPersistenceAdapter.createUser(user);
    }

    @Override
    public User createUser(User user) {
        existUser(user.getEmail());
        return this.userPersistenceAdapter.createUser(user);
    }
}
