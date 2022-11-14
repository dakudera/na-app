package tech.na_app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import tech.na_app.model.profile.SaveUserProfileRequest;
import tech.na_app.model.profile.SaveUserProfileResponse;
import tech.na_app.model.user.SaveNewUserRequest;
import tech.na_app.model.user.SaveNewUserResponse;
import tech.na_app.services.UserService;
import tech.na_app.utils.HelpUtil;

@Log4j2
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService saveNewUser;

    @PostMapping("/save_new_user")
    public SaveNewUserResponse saveNewUser(@RequestBody SaveNewUserRequest request) {
        String requestId = HelpUtil.getUUID();
        log.info(requestId + " Request to saveNewUser: " + request);
        return saveNewUser.saveNewUser(requestId, request);
    }

    @PostMapping("/save_user_profile")
    public SaveUserProfileResponse saveUserProfile(@RequestBody SaveUserProfileRequest request) {
        String requestId = HelpUtil.getUUID();
        log.info(requestId + " Request to saveUserProfile: " + request);
        return saveNewUser.saveUserProfile(requestId, request);
    }

}
