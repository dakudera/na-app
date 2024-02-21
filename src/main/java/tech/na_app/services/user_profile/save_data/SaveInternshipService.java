package tech.na_app.services.user_profile.save_data;

import tech.na_app.entity.user.User;
import tech.na_app.model.profile.SaveInternshipRequest;
import tech.na_app.model.profile.SaveInternshipResponse;

public interface SaveInternshipService {

    SaveInternshipResponse saveInternship(String requestId, User user, SaveInternshipRequest request);

}
