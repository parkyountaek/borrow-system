package com.borrow.system.apporganization.adapter.out;

import com.borrow.system.modulecore.domain.organization.Organization;

import java.util.List;

public interface LoadUseCase {
    List<Organization> getAllByName(String name);
}
