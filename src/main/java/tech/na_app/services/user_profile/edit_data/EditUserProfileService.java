package tech.na_app.services.user_profile.edit_data;

import tech.na_app.entity.user.User;
import tech.na_app.model.profile.EditUserProfileRequest;
import tech.na_app.model.profile.EditUserProfileResponse;

public interface EditUserProfileService {

    EditUserProfileResponse editUserProfile(String requestId, User userThatMakeRequest, EditUserProfileRequest request);

}
