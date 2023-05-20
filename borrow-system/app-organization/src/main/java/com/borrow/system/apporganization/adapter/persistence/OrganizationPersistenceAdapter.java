package com.borrow.system.apporganization.adapter.persistence;

import com.borrow.system.apporganization.adapter.out.LoadUseCase;
import com.borrow.system.modulecommon.exception.BusinessLogicException;
import com.borrow.system.modulecommon.exception.ExceptionCode;
import com.borrow.system.modulecore.organization.domain.Organization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrganizationPersistenceAdapter implements LoadUseCase {
    private final OrganizationRepository organizationRepository;

    public Organization saveOrganization(Organization organization) {
        return this.organizationRepository.save(organization);
    }

    public Organization getById(Long id) {
        return this.organizationRepository.findById(id)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ORGANIZATION_NOT_FOUND));
    }

    @Override
    public List<Organization> getAllByName(String name) {
        return this.organizationRepository.findAllByName(name);
    }
}
