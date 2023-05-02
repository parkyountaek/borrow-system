package com.borrow.system.appusermanagement.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.borrow.system.appusermanagement.application.port.out.LoadUser;
import com.borrow.system.modulecore.user.domain.User;

@Repository
public class UserPersistenceAdapter implements LoadUser {
    private final UserRepository userRepository;

    public UserPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(Long id) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
