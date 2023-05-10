package com.borrow.system.appusermanagement.adapter.persistence;

import java.util.Optional;

import com.borrow.system.modulecommon.exception.BusinessLogicException;
import com.borrow.system.modulecommon.exception.ExceptionCode;
import org.springframework.stereotype.Repository;

import com.borrow.system.appusermanagement.application.port.out.LoadUserCase;
import com.borrow.system.modulecore.user.domain.User;

@Repository
public class UserPersistenceAdapter implements LoadUserCase {
    private final UserRepository userRepository;

    public UserPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        return optionalUser.orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> optionalUser = this.userRepository.findByEmail(email);
        return optionalUser.orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }

    public User createUser(User user) {
        return this.userRepository.save(user);
    }
}
