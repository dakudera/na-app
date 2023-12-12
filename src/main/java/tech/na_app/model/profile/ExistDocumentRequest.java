package tech.na_app.model.profile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.na_app.entity.profile.HealthCertificate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ExistDocumentRequest {

    @NotNull
    private Integer userId;

    @Schema(
            example = "AE123456",
            type = "string"
    )
    @NotEmpty
    private String passport;

    @Schema(
            example = "1234567890",
            type = "string"
    )
    @NotEmpty
    private String ipn;

    @Schema(
            example = "1231231",
            type = "string"
    )
    @NotEmpty
    private String employment_history;

    @Schema(
            example = "12313",
            type = "string"
    )
    @NotEmpty
    private String military_registration_doc;
    @NotNull
    private HealthCertificate health_certificate;


}
