package tech.na_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.na_app.model.ApiException;
import tech.na_app.model.auth.AuthRequest;
import tech.na_app.model.auth.LoginResponse;
import tech.na_app.model.auth.TokenRefreshRequest;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.services.authorization.AuthorizationService;
import tech.na_app.utils.jwt.AuthChecker;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthorizationController {


    private final AuthorizationService authorizationService;
    private final AuthChecker authChecker;

    @GetMapping("/")
    public String welcome(@RequestHeader(name = "Authorization") String token) throws ApiException {
        authChecker.checkToken(token, UserRoleType.SUPER_ADMIN);

        return "Welcome to javatechie !!";
    }

    @PostMapping("/authenticate")
    public LoginResponse generateToken(@RequestBody AuthRequest authRequest) {
        return authorizationService.login(authRequest);
    }

    @PostMapping("/refreshToken")
    public LoginResponse refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        return authorizationService.refreshToken(request);
    }

}
