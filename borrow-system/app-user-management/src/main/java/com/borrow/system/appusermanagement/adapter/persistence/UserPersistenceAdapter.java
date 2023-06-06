package com.borrow.system.appusermanagement.adapter.persistence;

import com.borrow.system.appusermanagement.application.port.out.LoadPort;
import com.borrow.system.appusermanagement.application.port.in.SavePort;
import com.borrow.system.modulecore.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserPersistenceAdapter implements LoadPort, SavePort {
    private final UserRepository userRepository;

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }
}
