package tech.na_app.services.user_profile.save_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.profile.DrivingLicense;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.profile.driving_license.SaveInfoDrivingLicenseRequest;
import tech.na_app.model.profile.driving_license.SaveInfoDrivingLicenseResponse;
import tech.na_app.repository.DrivingLicenseRepository;

@Log4j2
@Service
@RequiredArgsConstructor
public class SaveInfoDrivingLicenseServiceImpl implements SaveInfoDrivingLicenseService {

    private final DrivingLicenseRepository drivingLicenseRepository;

    @Override
    public SaveInfoDrivingLicenseResponse saveInfoDrivingLicense(String requestId, SaveInfoDrivingLicenseRequest request) {
        try {
            if (drivingLicenseRepository.findByUserId(request.getUserId()).isPresent()) {
                throw new ApiException(400, "User already has driving license");
            }

            drivingLicenseRepository.save(
                    DrivingLicense.builder()
                            .userId(request.getUserId())
                            .categories(request.getCategories())
                            .date_issue(request.getDate_issue())
                            .date_end(request.getDate_end())
                            .build()
            );

            return new SaveInfoDrivingLicenseResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveInfoDrivingLicenseResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new SaveInfoDrivingLicenseResponse(new ErrorObject(500, "Something went wrong"));
        }
    }

}
