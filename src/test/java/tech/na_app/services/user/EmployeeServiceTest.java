package tech.na_app.services.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import tech.na_app.entity.profile.Profile;
import tech.na_app.entity.user.User;
import tech.na_app.entity.user.UserRolesStore;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.model.user.employee.GetAllEmployeeResponse;
import tech.na_app.repository.UserRepository;
import tech.na_app.repository.UserRolesStoreRepository;
import tech.na_app.utils.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
public class EmployeeServiceTest {

    @Mock
    private UserRolesStoreRepository userRolesStoreRepository;
    @Mock
    private UserRepository userRepository;

    private EmployeeService employeeService;


    @Before
    public void setUp() {
        employeeService = new EmployeeService(userRolesStoreRepository, userRepository);
    }

    @Test
    public void getAllEmployee$EmptyList() {
        User user = new User();

        GetAllEmployeeResponse allEmployee = employeeService.getAllEmployee(TestUtils.TEST_REQUEST_ID, user);
        assertNull(allEmployee.getEmployeeInfo());
        assertEquals(allEmployee.getError().getCode().intValue(), 0);
    }


    @Test
    public void getAllEmployee$Success() {
        User user = new User();
        user.setCompanyId(1);

        List<UserRolesStore> roles = new ArrayList<>();
        roles.add(
                UserRolesStore.builder()
                        .id(1)
                        .role(UserRoleType.DRIVER)
                        .description("Водій")
                        .build()
        );
        PowerMockito.when(userRolesStoreRepository.findAll()).thenReturn(roles);

        List<User> users = new ArrayList<>();
        Profile profile = new Profile();
        profile.setFio("Test");
        users.add(
                User.builder()
                        .id(1)
                        .role(UserRoleType.DRIVER)
                        .profile(profile)
                        .build()
        );

        PowerMockito.when(userRepository.findAllByCompanyId(any())).thenReturn(users);

        GetAllEmployeeResponse allEmployee = employeeService.getAllEmployee(TestUtils.TEST_REQUEST_ID, user);
        assertNotNull(allEmployee.getEmployeeInfo());
        assertEquals(allEmployee.getEmployeeInfo().get(0).getId().intValue(), 1);
        assertEquals(allEmployee.getEmployeeInfo().get(0).getRole(), "Водій");
        assertEquals(allEmployee.getEmployeeInfo().get(0).getFio(), "Test");
        assertEquals(allEmployee.getError().getCode().intValue(), 0);
    }

}