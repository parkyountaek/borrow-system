package com.borrow.system.appusermanagement.adapter.persistence;

import java.util.Optional;

import com.borrow.system.modulecommon.exception.BusinessLogicException;
import com.borrow.system.modulecommon.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import com.borrow.system.appusermanagement.adapter.out.LoadUserCase;
import com.borrow.system.modulecore.domain.user.User;

@Repository
@RequiredArgsConstructor
public class UserPersistenceAdapter implements LoadUserCase {
    private final UserRepository userRepository;

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

    @Override
    public void existUserByEmail(String email) {
        Optional<User> optionalUser = this.userRepository.findByEmail(email);
        if(optionalUser.isPresent())
            throw new BusinessLogicException(ExceptionCode.USER_ALREADY_EXIST);
    }

    public User saveUser(User user) {
        return this.userRepository.save(user);
    }
}
