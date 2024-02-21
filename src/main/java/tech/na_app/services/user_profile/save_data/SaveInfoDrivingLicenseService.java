package tech.na_app.services.user_profile.save_data;

import tech.na_app.model.profile.driving_license.SaveInfoDrivingLicenseRequest;
import tech.na_app.model.profile.driving_license.SaveInfoDrivingLicenseResponse;

public interface SaveInfoDrivingLicenseService {

    SaveInfoDrivingLicenseResponse saveInfoDrivingLicense(String requestId, SaveInfoDrivingLicenseRequest request);

}
