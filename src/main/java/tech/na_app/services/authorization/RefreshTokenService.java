package tech.na_app.services.authorization;

import org.springframework.web.bind.annotation.RequestBody;
import tech.na_app.model.auth.LoginResponse;
import tech.na_app.model.auth.TokenRefreshRequest;

import javax.validation.Valid;

public interface RefreshTokenService {

    LoginResponse refreshToken(TokenRefreshRequest request);

}
