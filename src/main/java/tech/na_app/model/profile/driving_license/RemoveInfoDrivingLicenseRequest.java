package tech.na_app.model.profile.driving_license;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RemoveInfoDrivingLicenseRequest {

    @Schema(
            example = "1"
    )
    private Integer id;

    @Schema(
            example = "1"
    )
    private Integer userId;
}
