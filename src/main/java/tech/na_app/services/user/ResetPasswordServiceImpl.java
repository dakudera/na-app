package tech.na_app.services.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.user.User;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.user.ResetPasswordRequest;
import tech.na_app.repository.UserRepository;
import tech.na_app.utils.jwt.PasswordUtils;

@Log4j2
@Service
@RequiredArgsConstructor
public class ResetPasswordServiceImpl implements ResetPasswordService {

    private final UserRepository userRepository;

    public ErrorObject resetPassword(String requestId, User user, ResetPasswordRequest request) {
        try {

            String encodeOldPassword = PasswordUtils.generateSecurePassword(request.getOldPassword(), user.getSalt());
            if (!encodeOldPassword.equals(user.getPassword())) {
                log.info(requestId + " Incorrect password");
                throw new ApiException(400, "Incorrect request data");
            }

            String encodeNewPassword = PasswordUtils.generateSecurePassword(request.getNewPassword(), user.getSalt());
            if (encodeNewPassword.equals(encodeOldPassword)) {
                log.info(requestId + " Incorrect password");
                throw new ApiException(400, "Incorrect request data");
            }

            String salt = PasswordUtils.getSalt();
            String passwordEncode = PasswordUtils.generateSecurePassword(request.getNewPassword(), salt);

            user.setSalt(salt);
            user.setPassword(passwordEncode);
            userRepository.save(user);

            return new ErrorObject(0);
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new ErrorObject(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new ErrorObject(500, "Something went wrong");
        }
    }

}
