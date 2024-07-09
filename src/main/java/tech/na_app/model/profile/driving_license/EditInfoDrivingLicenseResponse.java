package tech.na_app.model.profile.driving_license;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.exceptions.ErrorObject;

@Data
@Builder
@NoArgsConstructor
public class EditInfoDrivingLicenseResponse {

    private ErrorObject error;

    public EditInfoDrivingLicenseResponse(ErrorObject error) {
        this.error = error;
    }
}
