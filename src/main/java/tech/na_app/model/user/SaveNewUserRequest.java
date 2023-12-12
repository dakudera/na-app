package tech.na_app.model.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.utils.jwt.PasswordUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveNewUserRequest {

    @Schema(
            example = "new user",
            required = true
    )
    @NotEmpty
    private String login;

    @Schema(
            example = "secrete password",
            required = true
    )
    @NotEmpty
    @Pattern(regexp = PasswordUtils.PASSWORD_PATTERN)
    private String password;

    @Schema(
            example = "DRIVER",
            required = true
    )
    @NotNull
    private UserRoleType role;


    @Override
    public String toString() {
        return "SaveNewUserRequest{" +
                "login='" + login + '\'' +
                ", role=" + role +
                '}';
    }
}
