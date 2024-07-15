package tech.na_app.services.user_profile.edit_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.profile.Profile;
import tech.na_app.entity.user.User;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.profile.EditUserProfileRequest;
import tech.na_app.model.profile.EditUserProfileResponse;
import tech.na_app.repository.UserRepository;
import tech.na_app.services.user_profile.UserProfileAbs;

import java.util.Date;

@Log4j2
@Service
@RequiredArgsConstructor
public class EditUserProfileServiceImpl extends UserProfileAbs implements EditUserProfileService {

    private final UserRepository userRepository;


    public EditUserProfileResponse editUserProfile(String requestId, User userThatMakeRequest, EditUserProfileRequest request) {
        try {
            User userInfo = choosingUser(userThatMakeRequest, request.getId());

            CalculateUserAge userAge = new CalculateUserAge(request.getBirthday());
            Profile profile = Profile.builder()
                    .email(request.getEmail())
                    .phone(request.getPhone())
                    .fio(request.getFio())
                    .acc_order_number(request.getAcc_order_number())
                    .acc_order_date(request.getAcc_order_date())
                    .salary(request.getSalary())
                    .birthday(request.getBirthday())
                    .age(userAge.execute())
                    .previous_work_exp(request.getPrevious_work_exp())
                    .previous_info_work_mp(request.getPrevious_info_work_mp())
                    .sufficient_experience_mp(request.getSufficient_experience_mp())
                    .registration_address(request.getRegistration_address())
                    .actual_address(request.getActual_address())
                    .build();
            userInfo.setRole(request.getRole());
            userInfo.setProfile(profile);
            userInfo.setUpdate_date(new Date());
            userRepository.save(userInfo);

            return new EditUserProfileResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditUserProfileResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new EditUserProfileResponse(new ErrorObject(500, "Something went wrong"));
        }
    }

}
