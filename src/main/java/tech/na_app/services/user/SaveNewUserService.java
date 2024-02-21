package tech.na_app.services.user;

import tech.na_app.entity.user.User;
import tech.na_app.model.user.SaveNewUserRequest;
import tech.na_app.model.user.SaveNewUserResponse;

public interface SaveNewUserService {

    SaveNewUserResponse saveNewUser(String requestId, User user, SaveNewUserRequest request);

}
