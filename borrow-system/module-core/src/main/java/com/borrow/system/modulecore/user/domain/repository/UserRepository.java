package com.borrow.system.modulecore.user.domain.repository;

import com.borrow.system.modulecore.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
