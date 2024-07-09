package tech.na_app.services.user_profile.remove_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.profile.DrivingLicense;
import tech.na_app.entity.user.User;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.profile.driving_license.RemoveInfoDrivingLicenseRequest;
import tech.na_app.model.profile.driving_license.RemoveInfoDrivingLicenseResponse;
import tech.na_app.repository.DrivingLicenseRepository;
import tech.na_app.services.user_profile.UserProfileAbs;

@Log4j2
@Service
@RequiredArgsConstructor
public class RemoveInfoDrivingLicenseServiceImpl extends UserProfileAbs implements RemoveInfoDrivingLicenseService {

    private final DrivingLicenseRepository drivingLicenseRepository;

    @Override
    public RemoveInfoDrivingLicenseResponse removeInfoDrivingLicense(String requestId, User user, RemoveInfoDrivingLicenseRequest request) {
        try {
            User userInfo = choosingUser(user, request.getUserId());

            DrivingLicense drivingLicense = drivingLicenseRepository.findByUserId(userInfo.getId())
                    .orElseThrow(() -> new ApiException(400, "BAD REQUEST"));
            drivingLicenseRepository.delete(drivingLicense);
            return new RemoveInfoDrivingLicenseResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new RemoveInfoDrivingLicenseResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new RemoveInfoDrivingLicenseResponse(new ErrorObject(500, "Something went wrong"));
        }
    }
}
