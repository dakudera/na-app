package tech.na_app.services.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith({MockitoExtension.class})
public class EmployeeServiceImplTest {

    @Mock
    private UserRolesStoreRepository userRolesStoreRepository;
    @Mock
    private UserRepository userRepository;

    private EmployeeServiceImpl employeeServiceImpl;

    @BeforeEach
    public void setUp() {
        employeeServiceImpl = new EmployeeServiceImpl(userRolesStoreRepository, userRepository);
    }

    @Test
    public void getAllEmployee$EmptyList() {
        User user = new User();

        GetAllEmployeeResponse allEmployee = employeeServiceImpl.getAllEmployee(TestUtils.TEST_REQUEST_ID, user);
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
        when(userRolesStoreRepository.findAll()).thenReturn(roles);

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

        when(userRepository.findAllByCompanyId(any())).thenReturn(users);

        GetAllEmployeeResponse allEmployee = employeeServiceImpl.getAllEmployee(TestUtils.TEST_REQUEST_ID, user);
        assertNotNull(allEmployee.getEmployeeInfo());
        assertEquals(allEmployee.getEmployeeInfo().get(0).getId().intValue(), 1);
        assertEquals(allEmployee.getEmployeeInfo().get(0).getRole(), "Водій");
        assertEquals(allEmployee.getEmployeeInfo().get(0).getFio(), "Test");
        assertEquals(allEmployee.getError().getCode().intValue(), 0);
    }

}