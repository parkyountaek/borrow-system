package com.borrow.system.apporganization.application.port.out;

import com.borrow.system.modulecore.domain.organization.Organization;

import java.util.List;
import java.util.Optional;

public interface LoadPort {
    List<Organization> getAllByName(String name);
    Optional<Organization> findById(Long id);
}
