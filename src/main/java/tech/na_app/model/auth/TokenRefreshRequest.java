package tech.na_app.model.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TokenRefreshRequest {

    @Schema(
            example = "refresh token"
    )
    @NotBlank
    private String refreshToken;

}
