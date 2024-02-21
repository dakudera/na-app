package tech.na_app.services.user_profile.remove_data;

import tech.na_app.entity.user.User;
import tech.na_app.model.profile.driving_license.RemoveInfoDrivingLicenseRequest;
import tech.na_app.model.profile.driving_license.RemoveInfoDrivingLicenseResponse;

public interface RemoveInfoDrivingLicenseService {

    RemoveInfoDrivingLicenseResponse removeInfoDrivingLicense(String requestId, User user, RemoveInfoDrivingLicenseRequest request);

}
