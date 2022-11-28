package tech.na_app.model.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TokenRefreshRequest {

    @Schema(
            example = "dfd74a32-8573-47df-9b3d-943b4d864fa7"
    )
    @NotBlank
    private String refreshToken;

}
