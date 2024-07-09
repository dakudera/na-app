package tech.na_app.model.profile.driving_license;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.exceptions.ErrorObject;

@Data
@Builder
@NoArgsConstructor
public class SaveInfoDrivingLicenseResponse {

    private ErrorObject error;

    public SaveInfoDrivingLicenseResponse(ErrorObject error) {
        this.error = error;
    }
}
