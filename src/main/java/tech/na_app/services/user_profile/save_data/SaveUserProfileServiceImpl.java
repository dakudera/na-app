package tech.na_app.services.user_profile.save_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import tech.na_app.entity.profile.Profile;
import tech.na_app.entity.user.User;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.profile.SaveUserProfileRequest;
import tech.na_app.model.profile.SaveUserProfileResponse;
import tech.na_app.repository.UserRepository;
import tech.na_app.services.user.UserHelperComponent;

import java.util.Date;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class SaveUserProfileServiceImpl implements SaveUserProfileService {

    private final UserRepository userRepository;
    private final UserHelperComponent userHelperComponent;

    @Override
    public SaveUserProfileResponse saveUserProfile(String requestId, SaveUserProfileRequest request) {
        try {
            Optional<User> userOptional = userRepository.findById(new ObjectId(request.getId()));
            if (userOptional.isEmpty()) {
                log.info(requestId + " User was not found");
                throw new ApiException(400, "BAD_REQUEST");
            }

            User user = userOptional.get();
            user.setUpdate_date(new Date());

            Profile profile = Profile.builder()
                    .email(request.getEmail())
                    .phone(request.getPhone())
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
                    .build();
            user.setProfile(profile);
            userRepository.save(user);

            return new SaveUserProfileResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveUserProfileResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new SaveUserProfileResponse(new ErrorObject(500, "Something went wrong"));
        }
    }
}
