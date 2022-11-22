package tech.na_app.model.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.enums.UserRoleType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveNewUserRequest {

    @Schema(
            example = "new user",
            required = true
    )
    private String login;

    @Schema(
            example = "secrete password",
            required = true
    )
    private String password;

    @Schema(
            example = "DRIVER",
            required = true
    )
    private UserRoleType role;


    @Override
    public String toString() {
        return "SaveNewUserRequest{" +
                "login='" + login + '\'' +
                ", role=" + role +
                '}';
    }
}
