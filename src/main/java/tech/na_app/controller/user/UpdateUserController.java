package tech.na_app.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import tech.na_app.controller.AuthenticationStrategy;
import tech.na_app.controller.BaseController;
import tech.na_app.entity.user.User;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.user.ResetPasswordRequest;
import tech.na_app.services.user.ResetPasswordService;


@Log4j2
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UpdateUserController extends BaseController {

    private final AuthenticationStrategy authenticationStrategy;
    private final ResetPasswordService resetPasswordService;

    @PostMapping("/reset_password")
    public ErrorObject resetPassword(
            @RequestHeader(name = "Authorization") String token, @RequestBody ResetPasswordRequest request
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /resetPassword");
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + " User: " + user);
            return resetPasswordService.resetPassword(requestId, user, request);
        });
    }

}
