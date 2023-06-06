package com.borrow.system.apporganization.application.port.in;

import com.borrow.system.modulecore.domain.organization.Organization;

public interface SavePort {
    Organization saveOrganization(Organization organization);
}
