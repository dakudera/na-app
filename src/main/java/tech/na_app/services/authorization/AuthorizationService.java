package tech.na_app.services.authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tech.na_app.entity.auth.RefreshToken;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.auth.AuthRequest;
import tech.na_app.model.auth.LoginResponse;
import tech.na_app.model.auth.TokenRefreshRequest;
import tech.na_app.repository.RefreshTokenRepository;
import tech.na_app.repository.UserRepository;
import tech.na_app.utils.jwt.JwtUtil;
import tech.na_app.utils.jwt.PasswordUtils;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    @Value("${security.jwt.expiration}")
    private Long expirationTime;

    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public LoginResponse login(AuthRequest authRequest) {
        try {
            Optional<User> userOptional = userRepository.findByLogin(authRequest.getLogin());
            User user = userOptional.orElseThrow(() -> new ApiException(500, "invalid username/password"));
            if (!PasswordUtils.verifyUserPassword(authRequest.getPassword(), user.getPassword(), user.getSalt())) {
                return new LoginResponse(new ErrorObject(500, "invalid username/password"));
            }

            Date issueAt = new Date(System.currentTimeMillis());
            Date expDate = new Date(System.currentTimeMillis() + expirationTime);
            return LoginResponse.builder()
                    .accessToken("Bearer " + jwtUtil.generateToken(user, issueAt, expDate))
                    .refreshToken(refreshTokenService.createRefreshToken(user.getId()))
                    .issueAt(issueAt)
                    .expDate(expDate)
                    .role(user.getRole())
                    .error(new ErrorObject(0))
                    .build();
        } catch (Exception ex) {
            return new LoginResponse(new ErrorObject(500, "invalid username/password"));
        }
    }

    public LoginResponse refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        try {
            Optional<RefreshToken> refreshTokenOptional = refreshTokenService.findByToken(requestRefreshToken);
            RefreshToken refreshToken = refreshTokenOptional.orElseThrow(() -> new ApiException(500, "invalid username/password"));
            refreshTokenService.verifyExpiration(refreshToken);

            Optional<User> userOptional = userRepository.findById(refreshToken.getUserId());
            refreshTokenRepository.delete(refreshToken);
            Date issueAt = new Date(System.currentTimeMillis());
            Date expDate = new Date(System.currentTimeMillis() + expirationTime);
            return LoginResponse.builder()
                    .accessToken("Bearer " + jwtUtil.generateToken(userOptional.get(), issueAt, expDate))
                    .refreshToken(refreshTokenService.createRefreshToken(userOptional.get().getId()))
                    .issueAt(issueAt)
                    .expDate(expDate)
                    .role(userOptional.get().getRole())
                    .error(new ErrorObject(0))
                    .build();
        } catch (Exception e) {
            return new LoginResponse(new ErrorObject(500, "invalid username/password"));
        }
    }

}
