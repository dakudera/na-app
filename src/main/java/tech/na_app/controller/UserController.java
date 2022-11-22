package tech.na_app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.model.profile.SaveUserProfileRequest;
import tech.na_app.model.profile.SaveUserProfileResponse;
import tech.na_app.model.user.GetAllUserRolesResponse;
import tech.na_app.model.user.SaveNewUserRequest;
import tech.na_app.model.user.SaveNewUserResponse;
import tech.na_app.services.user.UserService;
import tech.na_app.utils.HelpUtil;
import tech.na_app.utils.jwt.AuthChecker;

@Log4j2
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final AuthChecker authChecker;
    private final UserService saveNewUser;

    @PostMapping("/save_new_user")
    public SaveNewUserResponse saveNewUser(
            @RequestHeader(name = "Authorization") String token, @RequestBody SaveNewUserRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.CHIEF_ACCOUNTANT);
            log.info(requestId + " Request to saveNewUser: " + request);
            SaveNewUserResponse response = saveNewUser.saveNewUser(requestId, user, request);
            log.info(requestId + " Response: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveNewUserResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @PostMapping("/save_user_profile")
    public SaveUserProfileResponse saveUserProfile(
            @RequestHeader(name = "Authorization") String token, @RequestBody SaveUserProfileRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            authChecker.checkToken(token, UserRoleType.CHIEF_ACCOUNTANT);
            log.info(requestId + " Request to saveUserProfile: " + request);
            SaveUserProfileResponse response = saveNewUser.saveUserProfile(requestId, request);
            log.info(requestId + " Response: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveUserProfileResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @GetMapping("/get_all_roles")
    public GetAllUserRolesResponse getAllUserRoles(
            @RequestHeader(name = "Authorization") String token
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            authChecker.checkToken(token, UserRoleType.CHIEF_ACCOUNTANT);
            log.info(requestId + " Request to saveUserProfile");
            GetAllUserRolesResponse response = saveNewUser.getAllUserRoles(requestId);
            log.info(requestId + " Response: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new GetAllUserRolesResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

}
