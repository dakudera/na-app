package tech.na_app.services.user_profile.save_data;

import tech.na_app.model.profile.SaveUserProfileRequest;
import tech.na_app.model.profile.SaveUserProfileResponse;

public interface SaveUserProfileService {

    SaveUserProfileResponse saveUserProfile(String requestId, SaveUserProfileRequest request);

}
