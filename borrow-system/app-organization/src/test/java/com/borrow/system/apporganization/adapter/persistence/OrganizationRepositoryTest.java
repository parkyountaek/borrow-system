package com.borrow.system.apporganization.adapter.persistence;

import com.borrow.system.apporganization.config.TestConfig;
import com.borrow.system.modulecore.domain.organization.Organization;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@Import(TestConfig.class)
class OrganizationRepositoryTest {
    @Autowired
    OrganizationRepository organizationRepository;
    Organization savedOrganization;

    @BeforeEach
    void beforeEach() {
        Organization organization = Organization.of("oname", "oaddress", "odetailAddress", "orepresentativeNumber", "ofaxNumber");
        savedOrganization = organizationRepository.save(organization);
    }

    @Nested
    @DisplayName("이름으로 조직을 찾는다.")
    class FindByNameTest {
        @Test
        @DisplayName("성공한다.")
        void success() {
            // given
            String name = "oname";

            // when
            Optional<Organization> optionalOrganization = organizationRepository.findByName(name);

            // then
            assertThat(optionalOrganization)
                    .isPresent()
                    .get().isEqualTo(savedOrganization);
        }

        @Test
        @DisplayName("실패 한다.")
        void fail() {
            // given
            String name = "name";

            // when
            Optional<Organization> optionalOrganization = organizationRepository.findByName(name);

            // then
            assertThat(optionalOrganization).isNotPresent();
        }
    }

    @Test
    void findAllByName() {
        // given
        String name = "oname";

        // when
        List<Organization> organizations = organizationRepository.findAllByName(name);

        // then
        assertThat(organizations).hasSize(1);
    }
}