package tech.na_app.model.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AuthRequest {

    @Schema(
            example = "user",
            type = "string"
    )
    @NotEmpty
    private String login;
    @Schema(
            example = "password",
            type = "string"
    )
    @NotEmpty
    private String password;

}
