package tech.na_app.services.authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.na_app.entity.user.User;
import tech.na_app.model.auth.LoginResponse;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.repository.UserRepository;
import tech.na_app.utils.jwt.PasswordUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl extends AuthorithationAbst implements LoginService {

    private final UserRepository userRepository;

    public LoginResponse login(String basicAuthToken) {
        try {
            var creds = extractCredentials(basicAuthToken);
            Optional<User> userOptional = userRepository.findByLogin(creds[0]);
            User user = userOptional.orElseThrow(() -> new ApiException(500, "invalid username/password"));
            if (!PasswordUtils.verifyUserPassword(creds[1], user.getPassword(), user.getSalt())) {
                return new LoginResponse(new ErrorObject(500, "invalid username/password"));
            }

            return buildLoginResponse(user);
        } catch (Exception ex) {
            return new LoginResponse(new ErrorObject(500, "invalid username/password"));
        }
    }

}
