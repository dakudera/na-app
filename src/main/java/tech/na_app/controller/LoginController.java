package tech.na_app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import tech.na_app.model.auth.LoginResponse;
import tech.na_app.services.UserService;
import tech.na_app.utils.HelpUtil;

@Log4j2
@RestController
@RequiredArgsConstructor
public class LoginController {


    private final UserService saveNewUser;

    @GetMapping("/login")
    public LoginResponse login(@RequestHeader String login, @RequestHeader String password) {
        String requestId = HelpUtil.getUUID();
        log.info(requestId + " Request to saveNewUser: login: " + login + " password: " + password);
        return saveNewUser.login(requestId, login, password);
    }

}
