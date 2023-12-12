package tech.na_app.model.profile.driving_license;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RemoveInfoDrivingLicenseRequest {

    @Schema(
            example = "1"
    )
    @NotEmpty
    private Integer userId;
}
