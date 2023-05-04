package com.borrow.system.appusermanagement.application.service;

import java.util.Optional;

import com.borrow.system.modulecommon.exception.BusinessLogicException;
import com.borrow.system.modulecommon.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import com.borrow.system.appusermanagement.persistence.UserPersistenceAdapterCase;
import com.borrow.system.appusermanagement.application.port.in.CreateUseCase;
import com.borrow.system.modulecore.user.domain.User;

@Service
public class UserService implements CreateUseCase {
    private final UserPersistenceAdapterCase userPersistenceAdapter;

    public UserService(UserPersistenceAdapterCase userPersistenceAdapter) {
        this.userPersistenceAdapter = userPersistenceAdapter;
    }

    @Override
    public User createUser() {
        // TODO Auto-generated method stub

        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
    }

    public User findVerifiedUser(String email) {
        Optional<User> optionalUser = this.userPersistenceAdapter.findByEmail(email);
        return optionalUser.orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }

    public void existUser(String email) {
        Optional<User> optionalUser = this.userPersistenceAdapter.findByEmail(email);
        if(optionalUser.isEmpty())
            throw new BusinessLogicException(ExceptionCode.USER_ALREADY_EXIST);
    }
    
}
