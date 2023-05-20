package com.borrow.system.apporganization.adapter.persistence;

import com.borrow.system.modulecommon.exception.BusinessLogicException;
import com.borrow.system.modulecore.organization.domain.Organization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class OrganizationPersistenceAdapterTest {
    OrganizationPersistenceAdapter organizationPersistenceAdapter;
    @Mock
    OrganizationRepository organizationRepository;

    @BeforeEach
    void beforeEach() {
        organizationPersistenceAdapter = new OrganizationPersistenceAdapter(organizationRepository);
    }

    @Test
    @DisplayName("조직을 생성한다.")
    void saveOrganizationTest() {
        // given
        Organization organization = Organization.of("name", "address", "detailAddress", "representativeNumber", "faxNumber");
        given(organizationRepository.save(organization))
                .willReturn(organization);

        // when
        Organization savedOrganization = organizationPersistenceAdapter.saveOrganization(organization);

        // then
        then(organizationRepository)
                .should()
                .save(organization);
        assertThat(savedOrganization).isEqualTo(organization);
    }

    @Nested
    @DisplayName("조직 아이디로 조직을 찾는다.")
    class GetByIdTest {
        @Test
        @DisplayName("성공 테스트")
        void successTest() {
            // given
            Long id = 1L;
            Organization organization = Organization.of("name", "address", "detailAddress", "representativeNumber", "faxNumber");
            given(organizationRepository.findById(id))
                    .willReturn(Optional.of(organization));

            // when
            Organization findOrganization = organizationPersistenceAdapter.getById(id);

            // then
            then(organizationRepository)
                    .should()
                    .findById(id);
            assertThat(findOrganization).isEqualTo(organization);
        }

        @Test
        @DisplayName("실패 테스트")
        void failTest() {
            // given
            Long id = 1L;
            given(organizationRepository.findById(id))
                    .willReturn(Optional.empty());

            // when & then
            assertThatThrownBy(() -> organizationPersistenceAdapter.getById(id))
                    .isInstanceOf(BusinessLogicException.class)
                    .hasMessage("ORGANIZATION_NOT_FOUND");
        }
    }

    @Test
    @DisplayName("이름으로 일치하는 조직들을 찾는다.")
    void getAllByNameTest() {
        // given
        String name = "name";
        List<Organization> organizations = List.of(Organization.of("name", "address", "detailAddress", "representativeNumber", "faxNumber"));
        given(organizationRepository.findAllByName(name))
                .willReturn(organizations);

        // when
        List<Organization> findOrganizations = organizationPersistenceAdapter.getAllByName(name);

        // then
        then(organizationRepository)
                .should()
                .findAllByName(name);
        assertThat(findOrganizations).isEqualTo(organizations);
    }
}