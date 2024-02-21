package tech.na_app.services.user_profile.remove_data;

import tech.na_app.entity.user.User;
import tech.na_app.model.profile.RemoveInternshipRequest;
import tech.na_app.model.profile.SaveInternshipResponse;

public interface RemoveInternshipService {

    SaveInternshipResponse removeInternship(String requestId, User user, RemoveInternshipRequest request);

}
