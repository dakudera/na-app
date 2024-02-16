package tech.na_app.services.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import tech.na_app.entity.user.User;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.auth.LoginResponse;
import tech.na_app.model.wrapper.LoginDateWrapper;
import tech.na_app.utils.jwt.JwtUtil;

import java.util.Date;

public abstract class AuthorithationAbst {

    @Value("${security.jwt.expiration}")
    private Long expirationTime;

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    CreateRefreshTokenService createRefreshTokenService;

    LoginDateWrapper getLoginDate() {
        long time = System.currentTimeMillis();
        return new LoginDateWrapper(new Date(time), new Date(time + expirationTime));
    }

    LoginResponse buildLoginResponse(User user) {
        LoginDateWrapper loginDate = getLoginDate();
        return LoginResponse.builder()
                .accessToken("Bearer " + jwtUtil.generateToken(user, loginDate))
                .refreshToken(createRefreshTokenService.createRefreshToken(user.getId()))
                .issueAt(loginDate.getIssueAt())
                .expDate(loginDate.getExpDate())
                .role(user.getRole())
                .error(new ErrorObject(0))
                .build();
    }

}
