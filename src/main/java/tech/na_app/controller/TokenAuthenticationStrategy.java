package tech.na_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tech.na_app.entity.user.User;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.utils.jwt.AuthChecker;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationStrategy implements AuthenticationStrategy {

    private final AuthChecker authChecker;


    @Override
    public User authenticate(String token, UserRoleType roleType) throws ApiException {
        return authChecker.checkToken(token, roleType);
    }
}
