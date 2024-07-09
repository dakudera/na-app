package tech.na_app.services.user;

import tech.na_app.entity.user.User;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.user.ResetPasswordRequest;

public interface ResetPasswordService {

    ErrorObject resetPassword(String requestId, User user, ResetPasswordRequest request);

}
