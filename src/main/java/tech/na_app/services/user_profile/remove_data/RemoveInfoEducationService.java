package tech.na_app.services.user_profile.remove_data;

import tech.na_app.entity.user.User;
import tech.na_app.model.profile.education.RemoveInfoEducationRequest;
import tech.na_app.model.profile.education.RemoveInfoEducationResponse;

public interface RemoveInfoEducationService {

    RemoveInfoEducationResponse removeInfoEducation(String requestId, User user, RemoveInfoEducationRequest request);

}
