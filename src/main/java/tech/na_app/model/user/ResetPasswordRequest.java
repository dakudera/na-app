package tech.na_app.model.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.na_app.utils.jwt.PasswordUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class ResetPasswordRequest {

    @Schema(
            example = "old pass"
    )
    @NotEmpty
    private String oldPassword;
    @Schema(
            example = "new password"
    )
    @NotEmpty
    @Pattern(regexp = PasswordUtils.PASSWORD_PATTERN)
    private String newPassword;

}
