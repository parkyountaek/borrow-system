package com.borrow.system.apporganization.adapter.persistence;

import com.borrow.system.modulecore.organization.domain.Organization;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.borrow.system.modulecore.organization.domain.QOrganization.*;

@Repository
@RequiredArgsConstructor
public class OrganizationRepositoryImpl implements OrganizationRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Organization> findAllByName(String name) {
        return this.jpaQueryFactory.select(organization)
                .from(organization)
                .where(organization.name.like("%"+name+"%"))
                .fetch();
    }
}
