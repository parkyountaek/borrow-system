package com.borrow.system.appusermanagement.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.borrow.system.appusermanagement.application.port.out.LoadUserCase;
import com.borrow.system.modulecore.user.domain.User;

@Repository
public class UserPersistenceAdapterCase implements LoadUserCase {
    private final UserRepository userRepository;

    public UserPersistenceAdapterCase(UserRepository userRepository) {
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
