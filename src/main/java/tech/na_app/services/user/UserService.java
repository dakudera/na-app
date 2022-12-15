package tech.na_app.services.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.company.Company;
import tech.na_app.entity.user.User;
import tech.na_app.entity.user.UserRolesStore;
import tech.na_app.entity.user.UserSequence;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.model.user.GetAllUserRolesResponse;
import tech.na_app.model.user.ResetPasswordRequest;
import tech.na_app.model.user.SaveNewUserRequest;
import tech.na_app.model.user.SaveNewUserResponse;
import tech.na_app.repository.CompanyRepository;
import tech.na_app.repository.UserRepository;
import tech.na_app.repository.UserRolesStoreRepository;
import tech.na_app.utils.SequenceGeneratorService;
import tech.na_app.utils.jwt.PasswordUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SequenceGeneratorService sequenceGeneratorService;
    private final CompanyRepository companyRepository;
    private final UserRolesStoreRepository userRolesStoreRepository;

    public SaveNewUserResponse saveNewUser(String requestId, User user, SaveNewUserRequest request) {
        try {
            if (request.getLogin() == null || request.getLogin().isEmpty()
                    || request.getPassword() == null || request.getPassword().isEmpty()) {
                throw new ApiException(400, "BAD REQUEST");
            }

            if (!PasswordUtils.isValid(request.getPassword())) {
                log.info(requestId + " New password not verified");
                throw new ApiException(400, "Incorrect request data");
            }

            Integer companyId = null;
            if (!request.getRole().equals(UserRoleType.SUPER_ADMIN) && !user.getRole().equals(UserRoleType.SUPER_ADMIN)) {
                Optional<Company> companyOptional = companyRepository.findById(user.getCompanyId());
                if (companyOptional.isPresent()) {
                    companyId = companyOptional.get().getId();
                }
            }

            UserSequence sequenceNumber = (UserSequence) sequenceGeneratorService.getSequenceNumber(User.SEQUENCE_NAME, UserSequence.class);

            String salt = PasswordUtils.getSalt();
            String passwordEncode = PasswordUtils.generateSecurePassword(request.getPassword(), salt);
            userRepository.save(
                    User.builder()
                            .id(sequenceNumber.getSeq())
                            .create_date(new Date())
                            .login(request.getLogin())
                            .password(passwordEncode)
                            .role(request.getRole())
                            .companyId(companyId)
                            .salt(salt)
                            .build()
            );

            return SaveNewUserResponse.builder()
                    .id(sequenceNumber.getSeq())
                    .error(new ErrorObject(0))
                    .build();
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveNewUserResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new SaveNewUserResponse(new ErrorObject(500, "Something went wrong"));
        }
    }

    public GetAllUserRolesResponse getAllUserRoles(String requestId) {
        try {
            List<UserRolesStore> all = userRolesStoreRepository.findAll();
            GetAllUserRolesResponse response = new GetAllUserRolesResponse(new ErrorObject(0));
            List<GetAllUserRolesResponse.Role> roles = new ArrayList<>();
            all.forEach(a -> roles.add(
                    GetAllUserRolesResponse.Role.builder()
                            .role(a.getRole())
                            .description(a.getDescription())
                            .build()
            ));

            response.setRoles(roles);
            return response;
        } catch (Exception e) {
            log.error(requestId + " Error: " + e.getMessage());
            return new GetAllUserRolesResponse(new ErrorObject(500, e.getMessage()));
        }
    }

    public ErrorObject resetPassword(String requestId, User user, ResetPasswordRequest request) {
        try {
            if (request.getNewPassword() == null || request.getNewPassword().isEmpty()
                    || request.getOldPassword() == null || request.getOldPassword().isEmpty()) {
                log.info(requestId + " Bad request: " + request);
                throw new ApiException(400, "BAD_REQUEST");
            }

            String encodeOldPassword = PasswordUtils.generateSecurePassword(request.getOldPassword(), user.getSalt());
            if (!encodeOldPassword.equals(user.getPassword())) {
                log.info(requestId + " Incorrect password");
                throw new ApiException(400, "Incorrect request data");
            }


            if (!PasswordUtils.isValid(request.getNewPassword())) {
                log.info(requestId + " New password not verified");
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
