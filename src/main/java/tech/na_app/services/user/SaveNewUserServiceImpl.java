package tech.na_app.services.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import tech.na_app.entity.company.Company;
import tech.na_app.entity.user.User;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.user.SaveNewUserRequest;
import tech.na_app.model.user.SaveNewUserResponse;
import tech.na_app.repository.CompanyRepository;
import tech.na_app.repository.UserRepository;
import tech.na_app.utils.jwt.PasswordUtils;

import java.util.Date;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class SaveNewUserServiceImpl implements SaveNewUserService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Override
    public SaveNewUserResponse saveNewUser(String requestId, User user, SaveNewUserRequest request) {
        try {
            ObjectId companyId = null;
            if (!request.getRole().equals(UserRoleType.SUPER_ADMIN) && !user.getRole().equals(UserRoleType.SUPER_ADMIN)) {
                Optional<Company> companyOptional = companyRepository.findById(user.getCompanyId());
                if (companyOptional.isPresent()) {
                    companyId = companyOptional.get().getId();
                }
            }


            String salt = PasswordUtils.getSalt();
            String passwordEncode = PasswordUtils.generateSecurePassword(request.getPassword(), salt);
            User savedUser = userRepository.save(
                    User.builder()
                            .create_date(new Date())
                            .login(request.getLogin())
                            .password(passwordEncode)
                            .role(request.getRole())
                            .companyId(companyId)
                            .salt(salt)
                            .build()
            );

            return SaveNewUserResponse.builder()
                    .id(savedUser.getId().toString())
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
}
