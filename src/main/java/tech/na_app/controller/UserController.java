package tech.na_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.na_app.model.user.TestAuthRequest;
import tech.na_app.model.user.TestAuthResponse;
import tech.na_app.services.AuthService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @PostMapping("test")
    public TestAuthResponse test(@RequestBody TestAuthRequest request) {
        return authService.auth(request);
    }

}
