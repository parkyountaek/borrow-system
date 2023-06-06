package com.borrow.system.appusermanagement.application.service;

import com.borrow.system.appusermanagement.application.port.in.SavePort;
import com.borrow.system.appusermanagement.application.port.in.UserCreateUseCase;
import com.borrow.system.appusermanagement.application.port.in.UserUpdateUseCase;
import com.borrow.system.appusermanagement.application.port.out.LoadPort;
import com.borrow.system.appusermanagement.application.port.out.UserLoadUseCase;
import com.borrow.system.modulecommon.exception.BusinessLogicException;
import com.borrow.system.modulecommon.exception.ExceptionCode;
import com.borrow.system.modulecore.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserCreateUseCase, UserUpdateUseCase, UserLoadUseCase {
    private final SavePort savePort;
    private final LoadPort loadPort;

    @Transactional(readOnly = true)
    public void existUser(String email) {
        loadPort.findByEmail(email)
                .ifPresent(user -> {
                    throw new BusinessLogicException(ExceptionCode.USER_ALREADY_EXIST);
                });
    }

    @Override
    public User createUser(User user) {
        existUser(user.getEmail());
        return savePort.saveUser(user);
    }

    @Override
    public User updateUser(User user) {
        User findUser = getUserByEmail(user.getEmail());
        findUser.updateProperty(user);

        return savePort.saveUser(findUser);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return loadPort.findByEmail(email)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return loadPort.findById(id)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }
}