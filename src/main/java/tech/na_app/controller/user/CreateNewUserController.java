package tech.na_app.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import tech.na_app.controller.AuthenticationStrategy;
import tech.na_app.controller.BaseController;
import tech.na_app.entity.user.User;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.model.user.SaveNewUserRequest;
import tech.na_app.model.user.SaveNewUserResponse;
import tech.na_app.services.user.SaveNewUserService;


@Log4j2
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class CreateNewUserController extends BaseController {

    private final AuthenticationStrategy authenticationStrategy;
    private final SaveNewUserService saveNewUserService;

    @PostMapping("/save_new_user")
    public SaveNewUserResponse saveNewUser(
            @RequestHeader(name = "Authorization") String token, @RequestBody SaveNewUserRequest request
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /saveNewUser");
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + " User: " + user);
            return saveNewUserService.saveNewUser(requestId, user, request);
        });
    }

}
