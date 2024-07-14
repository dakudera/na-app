package tech.na_app.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.na_app.entity.user.User;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.model.role_buttons.GetAllowedButtonsResponse;
import tech.na_app.services.role_buttons.RoleButtonsService;

@Log4j2
@RestController
@RequestMapping("role_buttons")
@RequiredArgsConstructor
public class RoleButtonsController extends BaseController {

    private final AuthenticationStrategy authenticationStrategy;
    private final RoleButtonsService roleButtonsService;

    @GetMapping("allowed")
    public GetAllowedButtonsResponse getAllowedButtons(
            @RequestHeader(name = "Authorization") String token
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /getAllowedButtons");
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + " User: " + user);
            return roleButtonsService.getAllowedButtons(requestId, user);
        });
    }
}
