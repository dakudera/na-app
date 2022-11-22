package tech.na_app.model.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AuthRequest {

    @Schema(
            example = "user",
            type = "string"
    )
    private String login;
    @Schema(
            example = "password",
            type = "string"
    )
    private String password;

}
