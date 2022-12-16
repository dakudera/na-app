package tech.na_app.model.profile.driving_license;

import lombok.Data;
import tech.na_app.model.ErrorObject;

@Data
public class RemoveInfoDrivingLicenseResponse {

    private ErrorObject error;

    public RemoveInfoDrivingLicenseResponse(ErrorObject error) {
        this.error = error;
    }
}
