package tech.na_app.model.profile.driving_license;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.na_app.model.enums.DriverLicenceCategory;

import java.util.Date;
import java.util.Set;

@Data
public class EditInfoDrivingLicenseRequest {

    @Schema(
            example = "1"
    )
    private Integer id;

    @Schema(
            example = "1"
    )
    private Integer userId;

    private Set<DriverLicenceCategory> categories;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Kiev")
    @Schema(
            example = "01.10.2015",
            pattern = "dd.MM.yyyy",
            type = "string"
    )
    private Date date_issue;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Kiev")
    @Schema(
            example = "01.10.2015",
            pattern = "dd.MM.yyyy",
            type = "string"
    )
    private Date date_end;
}
