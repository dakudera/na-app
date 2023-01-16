package tech.na_app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.model.user.GetAllUserRolesResponse;
import tech.na_app.model.user.ResetPasswordRequest;
import tech.na_app.model.user.SaveNewUserRequest;
import tech.na_app.model.user.SaveNewUserResponse;
import tech.na_app.model.user.employee.GetAllEmployeeResponse;
import tech.na_app.services.user.EmployeeService;
import tech.na_app.services.user.UserService;
import tech.na_app.utils.HelpUtil;
import tech.na_app.utils.jwt.AuthChecker;

@Log4j2
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final AuthChecker authChecker;
    private final UserService userService;
    private final EmployeeService employeeService;

    @PostMapping("/save_new_user")
    public SaveNewUserResponse saveNewUser(
            @RequestHeader(name = "Authorization") String token, @RequestBody SaveNewUserRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.CHIEF_ACCOUNTANT);
            log.info(requestId + " Request to /saveNewUser: " + request);
            log.info(requestId + " User: " + user);
            SaveNewUserResponse response = userService.saveNewUser(requestId, user, request);
            log.info(requestId + " Response from /saveNewUser: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveNewUserResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @GetMapping("/get_all_roles")
    public GetAllUserRolesResponse getAllUserRoles(
            @RequestHeader(name = "Authorization") String token
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.CHIEF_ACCOUNTANT);
            log.info(requestId + " Request to /getAllUserRoles");
            log.info(requestId + " User: " + user);
            GetAllUserRolesResponse response = userService.getAllUserRoles(requestId);
            log.info(requestId + " Response from /getAllUserRoles: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new GetAllUserRolesResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @PostMapping("/reset_password")
    public ErrorObject resetPassword(
            @RequestHeader(name = "Authorization") String token, @RequestBody ResetPasswordRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.WAREHOUSE_MANAGER);
            log.info(requestId + " Request to /resetPassword: " + request);
            log.info(requestId + " User: " + user);
            ErrorObject response = userService.resetPassword(requestId, user, request);
            log.info(requestId + " Response from /resetPassword: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new ErrorObject(e.getCode(), e.getMessage());
        }
    }

    @GetMapping("/get_employee_list")
    public GetAllEmployeeResponse getEmployeeList(
            @RequestHeader(name = "Authorization") String token
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.WAREHOUSE_MANAGER);
            log.info(requestId + " Request to /getEmployeeList");
            log.info(requestId + " User: " + user);
            GetAllEmployeeResponse response = employeeService.getAllEmployee(requestId, user);
            log.info(requestId + " Response from /getEmployeeList: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new GetAllEmployeeResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

}
