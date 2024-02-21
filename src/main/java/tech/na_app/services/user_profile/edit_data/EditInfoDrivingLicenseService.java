package tech.na_app.services.user_profile.edit_data;

import tech.na_app.entity.user.User;
import tech.na_app.model.profile.driving_license.EditInfoDrivingLicenseRequest;
import tech.na_app.model.profile.driving_license.EditInfoDrivingLicenseResponse;

public interface EditInfoDrivingLicenseService {

    EditInfoDrivingLicenseResponse editInfoDrivingLicense(String requestId, User user, EditInfoDrivingLicenseRequest request);

}
