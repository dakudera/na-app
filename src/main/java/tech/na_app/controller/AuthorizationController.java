package tech.na_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import tech.na_app.model.auth.LoginResponse;
import tech.na_app.model.auth.TokenRefreshRequest;
import tech.na_app.services.authorization.LoginService;
import tech.na_app.services.authorization.RefreshTokenService;
import tech.na_app.utils.ValidateHelper;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class AuthorizationController {

    private final LoginService loginServiceImpl;
    private final RefreshTokenService refreshTokenServiceImpl;


    @PostMapping("/authenticate")
    public LoginResponse generateToken(
            @RequestHeader(name = "Authorization") String basicAuthToken
    ) throws IOException {
        return loginServiceImpl.login(basicAuthToken);
    }

    @PostMapping("/refresh-token")
    public LoginResponse refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        ValidateHelper.validateInput(request);
        return refreshTokenServiceImpl.refreshToken(request);
    }

}
