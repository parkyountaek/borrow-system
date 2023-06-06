package com.borrow.system.apporganization.adapter.persistence;

import com.borrow.system.apporganization.application.port.in.SavePort;
import com.borrow.system.apporganization.application.port.out.LoadPort;
import com.borrow.system.modulecore.domain.organization.Organization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrganizationPersistenceAdapter implements LoadPort, SavePort {
    private final OrganizationRepository organizationRepository;

    @Override
    public Organization saveOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public Optional<Organization> findById(Long id) {
        return organizationRepository.findById(id);
    }

    @Override
    public List<Organization> getAllByName(String name) {
        return organizationRepository.findAllByName(name);
    }
}
