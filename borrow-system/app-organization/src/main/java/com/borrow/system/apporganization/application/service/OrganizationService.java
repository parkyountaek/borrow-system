package com.borrow.system.apporganization.application.service;

import com.borrow.system.apporganization.application.port.in.OrganizationCreateUseCase;
import com.borrow.system.apporganization.application.port.in.OrganizationUpdateUseCase;
import com.borrow.system.apporganization.application.port.in.SavePort;
import com.borrow.system.apporganization.application.port.out.LoadPort;
import com.borrow.system.apporganization.application.port.out.OrganizationLoadUseCase;
import com.borrow.system.modulecommon.exception.BusinessLogicException;
import com.borrow.system.modulecommon.exception.ExceptionCode;
import com.borrow.system.modulecore.domain.organization.Organization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrganizationService implements OrganizationCreateUseCase, OrganizationUpdateUseCase, OrganizationLoadUseCase {
    private final LoadPort loadPort;
    private final SavePort savePort;

    @Override
    public Organization createOrganization(Organization organization) {
        return savePort.saveOrganization(organization);
    }

    @Override
    public Organization updateOrganization(Organization organization) {
        Organization findOrganization = this.getOrganizationById(organization.getId());
        findOrganization.updateProperty(organization);
        return savePort.saveOrganization(findOrganization);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Organization> getOrganizationByName(String name) {
        return loadPort.getAllByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Organization getOrganizationById(Long id) {
        return loadPort.findById(id)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ORGANIZATION_NOT_FOUND));
    }
}
