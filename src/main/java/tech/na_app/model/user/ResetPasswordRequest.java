package tech.na_app.model.user;

import lombok.Data;

@Data
public class ResetPasswordRequest {

    private String oldPassword;
    private String newPassword;

}
