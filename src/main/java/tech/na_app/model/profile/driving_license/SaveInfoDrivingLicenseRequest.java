package tech.na_app.model.profile.driving_license;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import tech.na_app.model.enums.DriverLicenceCategory;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveInfoDrivingLicenseRequest {

    @Schema(
            example = "1"
    )
    @NotNull
    private ObjectId userId;

    @NotNull
    private Set<DriverLicenceCategory> categories;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Kiev")
    @Schema(
            example = "01.10.2015",
            pattern = "dd.MM.yyyy",
            type = "string"
    )
    @NotNull
    private Date date_issue;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Kiev")
    @Schema(
            example = "01.10.2015",
            pattern = "dd.MM.yyyy",
            type = "string"
    )
    @NotNull
    private Date date_end;
}
