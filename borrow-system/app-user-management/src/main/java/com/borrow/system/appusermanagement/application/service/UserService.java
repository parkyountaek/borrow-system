package com.borrow.system.appusermanagement.application.service;

import com.borrow.system.appusermanagement.application.port.in.UserUpdateUseCase;
import com.borrow.system.appusermanagement.application.port.out.UserGetUseCase;
import com.borrow.system.appusermanagement.util.CustomBeanUtils;
import org.springframework.stereotype.Service;

import com.borrow.system.appusermanagement.adapter.persistence.UserPersistenceAdapter;
import com.borrow.system.appusermanagement.application.port.in.UserCreateUseCase;
import com.borrow.system.modulecore.user.domain.User;

@Service
public class UserService implements UserCreateUseCase, UserUpdateUseCase, UserGetUseCase {
    private final UserPersistenceAdapter userPersistenceAdapter;

    public UserService(UserPersistenceAdapter userPersistenceAdapter) {
        this.userPersistenceAdapter = userPersistenceAdapter;
    }

    public User findVerifiedUser(String email) {
        return this.userPersistenceAdapter.getUserByEmail(email);
    }

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
        User findUser = findVerifiedUser(user.getEmail());
        findUser.updateProperty(user);

        return this.userPersistenceAdapter.saveUser(findUser);
    }

    @Override
    public User getUserByEmail(String email) {
        return this.userPersistenceAdapter.getUserByEmail(email);
    }

    @Override
    public User getUserById(Long id) {
        return this.userPersistenceAdapter.getUserById(id);
    }
}
