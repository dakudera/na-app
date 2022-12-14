package tech.na_app.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.model.role_buttons.GetAllowedButtonsResponse;
import tech.na_app.services.role_buttons.RoleButtonsService;
import tech.na_app.utils.HelpUtil;
import tech.na_app.utils.jwt.AuthChecker;

@Log4j2
@RestController
@RequestMapping("role_buttons")
@RequiredArgsConstructor
public class RoleButtonsController {

    private final AuthChecker authChecker;
    private final RoleButtonsService roleButtonsService;

    @GetMapping("get_allowed")
    public GetAllowedButtonsResponse getAllowedButtons(@RequestHeader(name = "Authorization") String token) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.WAREHOUSE_MANAGER);
            log.info(requestId + " Request to /getAllowedButtons");
            GetAllowedButtonsResponse response = roleButtonsService.getAllowedButtons(requestId, user);
            log.info(requestId + " Response from /getAllowedButtons: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new GetAllowedButtonsResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }
}
