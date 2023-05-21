package com.borrow.system.appusermanagement.adapter.persistence;

import com.borrow.system.modulecore.domain.user.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
