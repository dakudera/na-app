package tech.na_app.services.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import tech.na_app.entity.user.User;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.auth.LoginResponse;
import tech.na_app.model.wrapper.LoginDateWrapper;
import tech.na_app.utils.jwt.JwtUtil;

import java.io.IOException;
import java.util.Base64;
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

    String[] extractCredentials(String header) throws IOException {
        byte[] base64Token = header.substring(6).getBytes("UTF-8");
        byte[] decoded;
        try {
            decoded = Base64.getDecoder().decode(base64Token);
            String token = new String(decoded, "UTF-8");
            System.out.println(token);
            int delim = token.indexOf(":");
            if (delim == -1) {
                throw new IllegalArgumentException("Invalid basic authentication token");
            }
            return new String[] {token.substring(0, delim), token.substring(delim + 1)};
        } catch (IllegalArgumentException e) {
            throw new IOException("Failed to decode basic authentication token");
        }
    }

}
