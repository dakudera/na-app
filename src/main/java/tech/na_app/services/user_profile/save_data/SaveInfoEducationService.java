package tech.na_app.services.user_profile.save_data;

import tech.na_app.model.profile.education.SaveInfoEducationRequest;
import tech.na_app.model.profile.education.SaveInfoEducationResponse;

public interface SaveInfoEducationService {

    SaveInfoEducationResponse saveInfoEducation(String requestId, SaveInfoEducationRequest request);

}
