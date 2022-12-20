package tech.na_app.model.profile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.na_app.entity.profile.HealthCertificate;

@Data
public class ExistDocumentRequest {

    private Integer userId;

    @Schema(
            example = "AE123456",
            type = "string"
    )
    private String passport;

    @Schema(
            example = "1234567890",
            type = "string"
    )
    private String ipn;

    @Schema(
            example = "1231231",
            type = "string"
    )
    private String employment_history;

    @Schema(
            example = "12313",
            type = "string"
    )
    private String military_registration_doc;
    private HealthCertificate health_certificate;


}
