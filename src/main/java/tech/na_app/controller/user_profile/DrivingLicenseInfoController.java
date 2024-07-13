package tech.na_app.controller.user_profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import tech.na_app.controller.AuthenticationStrategy;
import tech.na_app.controller.BaseController;
import tech.na_app.entity.user.User;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.model.profile.driving_license.*;
import tech.na_app.services.user_profile.edit_data.EditInfoDrivingLicenseService;
import tech.na_app.services.user_profile.remove_data.RemoveInfoDrivingLicenseService;
import tech.na_app.services.user_profile.save_data.SaveInfoDrivingLicenseService;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("user_profile/driving_license")
public class DrivingLicenseInfoController extends BaseController {

    private final AuthenticationStrategy authenticationStrategy;
    private final EditInfoDrivingLicenseService editInfoDrivingLicenseService;
    private final SaveInfoDrivingLicenseService saveInfoDrivingLicenseService;
    private final RemoveInfoDrivingLicenseService removeInfoDrivingLicenseService;

    @PostMapping("/save")
    public SaveInfoDrivingLicenseResponse saveInfoDrivingLicense(
            @RequestHeader(name = "Authorization") String token, @RequestBody SaveInfoDrivingLicenseRequest request
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /saveInfoDrivingLicense: " + request);
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + " User: " + user);
            return saveInfoDrivingLicenseService.saveInfoDrivingLicense(requestId, request);
        });
    }

    @PutMapping("/update")
    public EditInfoDrivingLicenseResponse editInfoDrivingLicense(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody EditInfoDrivingLicenseRequest request
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /editInfoDrivingLicense: " + request);
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + " User: " + user);
            return editInfoDrivingLicenseService.editInfoDrivingLicense(requestId, user, request);
        });
    }

    @DeleteMapping("/remove")
    public RemoveInfoDrivingLicenseResponse removeInfoDrivingLicense(
            @RequestHeader(name = "Authorization") String token, @RequestBody RemoveInfoDrivingLicenseRequest request
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /removeInfoDrivingLicense: " + request);
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + " User: " + user);
            return removeInfoDrivingLicenseService.removeInfoDrivingLicense(requestId, user, request);
        });
    }

}
