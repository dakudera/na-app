package tech.na_app.model.profile.driving_license;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import tech.na_app.model.enums.DriverLicenceCategory;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;

public record EditInfoDrivingLicenseRequest(
        @Schema(
                example = "1"
        )
        @NotEmpty
        String userId,
        @NotEmpty
        Set<DriverLicenceCategory> categories,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Kiev")
        @Schema(
                example = "01.10.2015",
                pattern = "dd.MM.yyyy",
                type = "string"
        )
        @NotEmpty
        Date date_issue,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Kiev")
        @Schema(
                example = "01.10.2015",
                pattern = "dd.MM.yyyy",
                type = "string"
        )
        @NotEmpty
        Date date_end
) {

}
