package tech.na_app.model.profile;

import io.swagger.v3.oas.annotations.media.Schema;
import tech.na_app.entity.profile.HealthCertificate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record ExistDocumentRequest(
        @NotNull String userId,
        @Schema(
                example = "AE123456",
                type = "string"
        )
        @NotEmpty String passport,
        @Schema(
                example = "1234567890",
                type = "string"
        )
        @NotEmpty String ipn,
        @Schema(
                example = "1231231",
                type = "string"
        )
        @NotEmpty String employment_history,

        @Schema(
                example = "12313",
                type = "string"
        )
        @NotEmpty
        String military_registration_doc,
        @NotNull
        HealthCertificate health_certificate
) {

}
