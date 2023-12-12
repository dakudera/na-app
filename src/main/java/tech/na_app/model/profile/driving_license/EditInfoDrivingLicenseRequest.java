package tech.na_app.model.profile.driving_license;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.enums.DriverLicenceCategory;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditInfoDrivingLicenseRequest {

    @Schema(
            example = "1"
    )
    @NotEmpty
    private Integer userId;

    @NotEmpty
    private Set<DriverLicenceCategory> categories;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Kiev")
    @Schema(
            example = "01.10.2015",
            pattern = "dd.MM.yyyy",
            type = "string"
    )
    @NotEmpty
    private Date date_issue;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Kiev")
    @Schema(
            example = "01.10.2015",
            pattern = "dd.MM.yyyy",
            type = "string"
    )
    @NotEmpty
    private Date date_end;
}
