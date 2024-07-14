package tech.na_app.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.na_app.controller.AuthenticationStrategy;
import tech.na_app.controller.BaseController;
import tech.na_app.entity.user.User;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.model.user.GetAllUserRolesResponse;
import tech.na_app.model.user.employee.GetAllEmployeeResponse;
import tech.na_app.services.user.EmployeeService;
import tech.na_app.services.user.GetAllUserRolesService;

@Log4j2
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class GetUserController extends BaseController {

    private final AuthenticationStrategy authenticationStrategy;
    private final EmployeeService employeeService;
    private final GetAllUserRolesService getAllUserRolesService;


    @GetMapping("/get_all_roles")
    public GetAllUserRolesResponse getAllUserRoles(
            @RequestHeader(name = "Authorization") String token
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /getAllUserRoles");
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + " User: " + user);
            return getAllUserRolesService.getAllUserRoles(requestId);
        });
    }

    @GetMapping("/get_employee_list")
    public GetAllEmployeeResponse getEmployeeList(
            @RequestHeader(name = "Authorization") String token
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /getEmployeeList");
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + " User: " + user);
            return employeeService.getAllEmployee(requestId, user);
        });
    }

}
