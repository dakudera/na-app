package tech.na_app.services.user_profile.get_data;

import tech.na_app.entity.user.User;
import tech.na_app.model.profile.GetUserProfileRequest;
import tech.na_app.model.profile.GetUserProfileResponse;

public interface GetUserProfileService {

    GetUserProfileResponse getUserProfile(String requestId, User user, GetUserProfileRequest request);

}
