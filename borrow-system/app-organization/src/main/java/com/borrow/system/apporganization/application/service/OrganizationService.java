package com.borrow.system.apporganization.application.service;

import com.borrow.system.apporganization.adapter.persistence.OrganizationPersistenceAdapter;
import com.borrow.system.apporganization.application.port.in.OrganizationCreateUseCase;
import com.borrow.system.apporganization.application.port.in.OrganizationUpdateUseCase;
import com.borrow.system.apporganization.application.port.out.OrganizationGetUseCase;
import com.borrow.system.modulecore.domain.organization.Organization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrganizationService implements OrganizationCreateUseCase, OrganizationUpdateUseCase, OrganizationGetUseCase {
    private final OrganizationPersistenceAdapter organizationPersistenceAdapter;

    @Override
    public Organization createOrganization(Organization organization) {
        return this.organizationPersistenceAdapter.saveOrganization(organization);
    }

    @Override
    public Organization updateOrganization(Organization organization) {
        Organization findOrganization = this.getOrganizationById(organization.getId());
        findOrganization.updateProperty(organization);
        return this.organizationPersistenceAdapter.saveOrganization(findOrganization);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Organization> getOrganizationByName(String name) {
        return this.organizationPersistenceAdapter.getAllByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Organization getOrganizationById(Long id) {
        return this.organizationPersistenceAdapter.getById(id);
    }
}
