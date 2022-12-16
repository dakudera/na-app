package tech.na_app.model.profile.driving_license;

import lombok.Data;
import tech.na_app.model.ErrorObject;

@Data
public class EditInfoDrivingLicenseResponse {

    private ErrorObject error;

    public EditInfoDrivingLicenseResponse(ErrorObject error) {
        this.error = error;
    }
}
