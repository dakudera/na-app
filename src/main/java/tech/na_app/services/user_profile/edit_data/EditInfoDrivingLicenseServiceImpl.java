package tech.na_app.services.user_profile.edit_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.profile.DrivingLicense;
import tech.na_app.entity.user.User;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.profile.driving_license.EditInfoDrivingLicenseRequest;
import tech.na_app.model.profile.driving_license.EditInfoDrivingLicenseResponse;
import tech.na_app.repository.DrivingLicenseRepository;
import tech.na_app.services.user_profile.UserProfileAbs;

@Log4j2
@Service
@RequiredArgsConstructor
public class EditInfoDrivingLicenseServiceImpl extends UserProfileAbs implements EditInfoDrivingLicenseService {

    private final DrivingLicenseRepository drivingLicenseRepository;

    @Override
    public EditInfoDrivingLicenseResponse editInfoDrivingLicense(String requestId, User user, EditInfoDrivingLicenseRequest request) {
        try {
            User userInfo = choosingUser(user, request.getUserId());

            DrivingLicense drivingLicense = drivingLicenseRepository.findByUserId(userInfo.getId())
                    .orElseThrow(() -> new ApiException(400, "BAD REQUEST"));
            drivingLicense.setCategories(request.getCategories());
            drivingLicense.setDate_issue(request.getDate_issue());
            drivingLicense.setDate_end(request.getDate_end());
            drivingLicenseRepository.save(drivingLicense);

            return new EditInfoDrivingLicenseResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditInfoDrivingLicenseResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new EditInfoDrivingLicenseResponse(new ErrorObject(500, "Something went wrong"));
        }
    }
}
