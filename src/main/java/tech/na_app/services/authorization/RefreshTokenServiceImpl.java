package tech.na_app.services.authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tech.na_app.entity.auth.RefreshToken;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.auth.LoginResponse;
import tech.na_app.model.auth.TokenRefreshRequest;
import tech.na_app.repository.RefreshTokenRepository;
import tech.na_app.repository.UserRepository;

import javax.validation.Valid;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl extends AuthorithationAbst implements RefreshTokenService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public LoginResponse refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        try {
            Optional<RefreshToken> refreshTokenOptional = createRefreshTokenService.findByToken(requestRefreshToken);
            RefreshToken refreshToken = refreshTokenOptional.orElseThrow(() -> new ApiException(500, "invalid username/password"));
            createRefreshTokenService.verifyExpiration(refreshToken);

            Optional<User> userOptional = userRepository.findById(refreshToken.getUserId());
            refreshTokenRepository.delete(refreshToken);

            return buildLoginResponse(userOptional.get());
        } catch (Exception e) {
            return new LoginResponse(new ErrorObject(500, "invalid username/password"));
        }
    }

}
