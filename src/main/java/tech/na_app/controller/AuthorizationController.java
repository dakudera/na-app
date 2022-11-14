package tech.na_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.na_app.model.TokenRefreshRequest;
import tech.na_app.model.auth.AuthRequest;
import tech.na_app.model.auth.LoginResponse;
import tech.na_app.services.authorization.AuthorizationService;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to javatechie !!";
    }

    @PostMapping("/authenticate")
    public LoginResponse generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        return authorizationService.login(authRequest);
    }

    @PostMapping("/refreshToken")
    public LoginResponse refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        return authorizationService.refreshToken(request);
    }

}
