package tech.na_app.services.authorization;

import tech.na_app.model.auth.LoginResponse;

public interface LoginService {

    LoginResponse login(String basicAuthToken);

}
