package tech.na_app.services.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.company.Company;
import tech.na_app.entity.profile.Profile;
import tech.na_app.entity.user.User;
import tech.na_app.entity.user.UserRolesStore;
import tech.na_app.entity.user.UserSequence;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.model.profile.SaveUserProfileRequest;
import tech.na_app.model.profile.SaveUserProfileResponse;
import tech.na_app.model.user.GetAllUserRolesResponse;
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
    private final UserHelperComponent userHelperComponent;
    private final UserRolesStoreRepository userRolesStoreRepository;

    public SaveNewUserResponse saveNewUser(String requestId, User user, SaveNewUserRequest request) {
        try {
            if (request.getLogin() == null || request.getLogin().isEmpty()
                    || request.getPassword() == null || request.getPassword().isEmpty()) {
                throw new ApiException(400, "BAD REQUEST");
            }

            Integer companyId = null;
            if (!request.getRole().equals(UserRoleType.SUPER_ADMIN)) {
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
        }
    }


    public SaveUserProfileResponse saveUserProfile(String requestId, SaveUserProfileRequest request) {
        try {
            if (request.getId() == null) {
                throw new ApiException(400, "BAD_REQUEST");
            }

            Optional<User> userOptional = userRepository.findById(request.getId());
            if (userOptional.isEmpty()) {
                log.info(requestId + " User was not found");
                throw new ApiException(400, "BAD_REQUEST");
            }

            User user = userOptional.get();
            user.setUpdate_date(new Date());
            Profile profile = Profile.builder()
                    .email(request.getEmail())
                    .fio(request.getFio())
                    .acc_order_number(request.getAcc_order_number())
                    .acc_order_date(request.getAcc_order_date())
                    .salary(request.getSalary())
                    .birthday(request.getBirthday())
                    .age(userHelperComponent.calculateAge(request.getBirthday()))
                    .previous_work_exp(request.getPrevious_work_exp())
                    .previous_info_work_mp(request.getPrevious_info_work_mp())
                    .sufficient_experience_mp(request.getSufficient_experience_mp())
                    .registration_address(request.getRegistration_address())
                    .actual_address(request.getActual_address())
                    .education(request.getEducation())
                    .driving_license(request.getDriving_license())
                    .available_documents(request.getAvailable_documents())
                    .internship(request.getInternship())
                    .build();
            user.setProfile(profile);
            userRepository.save(user);

            return new SaveUserProfileResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveUserProfileResponse(new ErrorObject(e.getCode(), e.getMessage()));
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

}
