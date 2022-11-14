package tech.na_app.model.auth;

import lombok.Data;

@Data
public class AuthRequest {

    private String login;
    private String password;

}
