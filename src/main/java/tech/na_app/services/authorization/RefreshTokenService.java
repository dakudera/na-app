package tech.na_app.services.authorization;

import tech.na_app.model.auth.LoginResponse;
import tech.na_app.model.auth.TokenRefreshRequest;

public interface RefreshTokenService {

    LoginResponse refreshToken(TokenRefreshRequest request);

}
