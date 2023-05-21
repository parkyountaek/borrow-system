package com.borrow.system.appusermanagement.application.service;

import com.borrow.system.appusermanagement.application.port.in.UserUpdateUseCase;
import com.borrow.system.appusermanagement.application.port.out.UserGetUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.borrow.system.appusermanagement.adapter.persistence.UserPersistenceAdapter;
import com.borrow.system.appusermanagement.application.port.in.UserCreateUseCase;
import com.borrow.system.modulecore.domain.user.User;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserCreateUseCase, UserUpdateUseCase, UserGetUseCase {
    private final UserPersistenceAdapter userPersistenceAdapter;

    @Transactional(readOnly = true)
    public void existUser(String email) {
        this.userPersistenceAdapter.existUserByEmail(email);
    }

    @Override
    public User createUser(User user) {
        existUser(user.getEmail());
        return this.userPersistenceAdapter.saveUser(user);
    }

    @Override
    public User updateUser(User user) {
        User findUser = this.getUserByEmail(user.getEmail());
        findUser.updateProperty(user);

        return this.userPersistenceAdapter.saveUser(findUser);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return this.userPersistenceAdapter.getUserByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return this.userPersistenceAdapter.getUserById(id);
    }
}