package tech.na_app.model.auth;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

public record TokenRefreshRequest(
        @Schema(
                example = "dfd74a32-8573-47df-9b3d-943b4d864fa7"
        )
        @NotBlank
        String refreshToken
) {

}
