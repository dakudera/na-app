package tech.na_app.controller;

import tech.na_app.entity.user.User;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.model.exceptions.ApiException;

public interface AuthenticationStrategy {

    User authenticate(String token, UserRoleType roleType) throws ApiException;

}
