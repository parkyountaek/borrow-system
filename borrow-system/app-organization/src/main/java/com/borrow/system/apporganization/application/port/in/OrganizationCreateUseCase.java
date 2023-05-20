package com.borrow.system.apporganization.application.port.in;

import com.borrow.system.modulecore.organization.domain.Organization;

public interface OrganizationCreateUseCase {
    Organization createOrganization(Organization organization);
}
