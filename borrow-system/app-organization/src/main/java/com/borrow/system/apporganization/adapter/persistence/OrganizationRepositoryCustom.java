package com.borrow.system.apporganization.adapter.persistence;

import com.borrow.system.modulecore.organization.domain.Organization;

import java.util.List;

public interface OrganizationRepositoryCustom {
    List<Organization> findAllByName(String name);
}
