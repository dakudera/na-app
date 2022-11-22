package tech.na_app.model.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ResetPasswordRequest {

    @Schema(
            example = "old pass"
    )
    private String oldPassword;
    @Schema(
            example = "new password"
    )
    private String newPassword;

}
