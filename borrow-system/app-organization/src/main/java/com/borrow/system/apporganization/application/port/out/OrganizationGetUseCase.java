package com.borrow.system.apporganization.application.port.out;

import com.borrow.system.modulecore.domain.organization.Organization;

import java.util.List;

public interface OrganizationGetUseCase {
    List<Organization> getOrganizationByName(String name);

    Organization getOrganizationById(Long id);
}
