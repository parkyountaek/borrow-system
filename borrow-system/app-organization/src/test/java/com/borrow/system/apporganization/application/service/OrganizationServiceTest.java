package com.borrow.system.apporganization.application.service;

import com.borrow.system.apporganization.application.port.in.SavePort;
import com.borrow.system.apporganization.application.port.out.LoadPort;
import com.borrow.system.modulecommon.exception.BusinessLogicException;
import com.borrow.system.modulecore.domain.organization.Organization;
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
class OrganizationServiceTest {
    OrganizationService organizationService;
    @Mock
    SavePort savePort;
    @Mock
    LoadPort loadPort;

    @BeforeEach
    void beforeEach() {
        organizationService = new OrganizationService(loadPort, savePort);
    }

    @Test
    @DisplayName("회원을 생성하는 서비스 테스트")
    void createOrganizationTest() {
        // given
        Organization organization = Organization.of("name", "address", "detailAddress", "representativeNumber", "faxNumber");
        given(savePort.saveOrganization(organization))
                .willReturn(organization);

        // when
        Organization saveOrganization = organizationService.createOrganization(organization);

        // then
        then(savePort)
                .should()
                .saveOrganization(organization);
        assertThat(saveOrganization).isEqualTo(organization);
    }

    @Test
    @DisplayName("조직 정보를 변경하는 서비스 테스트")
    void updateOrganizationTest() {
        // given
        Long id = 1L;
        Organization organization = new Organization(id, "name", "address", "detailAddress", "representativeNumber", "faxNumber");
        Organization updatingOrganization = new Organization(id, "changeName", "address", "detailAddress", "representativeNumber", "faxNumber");
        given(loadPort.findById(id))
                .willReturn(Optional.of(organization));
        organization.updateProperty(updatingOrganization);
        given(savePort.saveOrganization(organization))
                .willReturn(organization);

        // when
        Organization updatedOrganization = organizationService.updateOrganization(updatingOrganization);

        // then
        then(loadPort)
                .should()
                .findById(id);
        then(savePort)
                .should()
                .saveOrganization(organization);
        assertThat(updatedOrganization).isEqualTo(organization);
    }

    @Test
    @DisplayName("이름으로 조직 조회하는 로직 테스트")
    void getOrganizationByNameTest() {
        // given
        String name = "name";
        List<Organization> organizations = List.of(Organization.of("name", "address", "detailAddress", "representativeNumber", "faxNumber"));
        given(loadPort.getAllByName(name))
                .willReturn(organizations);

        // when
        List<Organization> findOrganizations = organizationService.getOrganizationByName(name);

        // then
        then(loadPort)
                .should()
                .getAllByName(name);
        assertThat(findOrganizations).isEqualTo(organizations);
    }

    @Nested
    @DisplayName("아이디로 조직을 조회하는 로직 테스트")
    class GetOrganizationByIdTest {
        @Test
        @DisplayName("성공 테스트")
        void successTest() {
            // given
            Long id = 1L;
            Organization organization = Organization.of("name", "address", "detailAddress", "representativeNumber", "faxNumber");
            given(loadPort.findById(id))
                    .willReturn(Optional.of(organization));

            // when
            Organization findOrganization = organizationService.getOrganizationById(id);

            // then
            then(loadPort)
                    .should()
                    .findById(id);
            assertThat(findOrganization).isEqualTo(organization);
        }

        @Test
        @DisplayName("실패 테스트")
        void failTest() {
            // given
            Long id = 1L;
            given(loadPort.findById(id))
                    .willReturn(Optional.empty());

            // when & then
            assertThatThrownBy(() -> organizationService.getOrganizationById(id))
                    .isInstanceOf(BusinessLogicException.class)
                    .hasMessage("ORGANIZATION_NOT_FOUND");
        }
    }
}