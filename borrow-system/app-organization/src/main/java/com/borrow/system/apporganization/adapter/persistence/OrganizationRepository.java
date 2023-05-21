package com.borrow.system.apporganization.adapter.persistence;

import com.borrow.system.modulecore.domain.organization.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long>, OrganizationRepositoryCustom {
    Optional<Organization> findByName(String name);
}
