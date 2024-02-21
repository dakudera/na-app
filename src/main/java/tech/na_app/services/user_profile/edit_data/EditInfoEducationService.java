package tech.na_app.services.user_profile.edit_data;

import tech.na_app.entity.user.User;
import tech.na_app.model.profile.education.EditInfoEducationRequest;
import tech.na_app.model.profile.education.EditInfoEducationResponse;

public interface EditInfoEducationService {

    EditInfoEducationResponse editInfoEducation(String requestId, User user, EditInfoEducationRequest request);

}
