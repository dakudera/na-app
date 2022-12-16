package tech.na_app.model.profile.driving_license;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.ErrorObject;

@Data
@NoArgsConstructor
public class SaveInfoDrivingLicenseResponse {

    private ErrorObject error;

    public SaveInfoDrivingLicenseResponse(ErrorObject error) {
        this.error = error;
    }
}
