package tech.na_app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import tech.na_app.entity.user.User;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.model.profile.*;
import tech.na_app.model.profile.driving_license.*;
import tech.na_app.model.profile.education.*;
import tech.na_app.services.user_profile.edit_data.EditInfoDrivingLicenseService;
import tech.na_app.services.user_profile.edit_data.EditInfoEducationService;
import tech.na_app.services.user_profile.edit_data.EditUserProfileService;
import tech.na_app.services.user_profile.get_data.GetUserProfileService;
import tech.na_app.services.user_profile.remove_data.RemoveInfoDrivingLicenseService;
import tech.na_app.services.user_profile.remove_data.RemoveInfoEducationService;
import tech.na_app.services.user_profile.remove_data.RemoveInternshipService;
import tech.na_app.services.user_profile.save_data.*;
import tech.na_app.utils.HelpUtil;
import tech.na_app.utils.ValidateHelper;
import tech.na_app.utils.jwt.AuthChecker;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("user_profile")
public class UserProfileController {

    private final EditUserProfileService editUserProfileService;
    private final AuthChecker authChecker;
    private final SaveInfoEducationService saveInfoEducationService;
    private final SaveInfoDrivingLicenseService saveInfoDrivingLicenseService;
    private final EditInfoEducationService editInfoEducationService;
    private final EditInfoDrivingLicenseService editInfoDrivingLicenseService;
    private final RemoveInfoEducationService removeInfoEducationService;
    private final RemoveInfoDrivingLicenseService removeInfoDrivingLicenseService;
    private final SaveInternshipService saveInternshipService;
    private final RemoveInternshipService removeInternshipService;
    private final GetUserProfileService getUserProfileService;
    private final SaveUserProfileService saveUserProfileService;
    private final SaveExistDocumentService saveExistDocumentService;

    @PostMapping("/save_info_driving_license")
    public SaveInfoDrivingLicenseResponse saveInfoDrivingLicense(
            @RequestHeader(name = "Authorization") String token, @RequestBody SaveInfoDrivingLicenseRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.WAREHOUSE_MANAGER);
            ValidateHelper.validateInput(request);
            log.info(requestId + " Request to /saveInfoDrivingLicense: " + request);
            log.info(requestId + " User: " + user);
            SaveInfoDrivingLicenseResponse response = saveInfoDrivingLicenseService.saveInfoDrivingLicense(requestId, request);
            log.info(requestId + " Response from /saveInfoDrivingLicense: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveInfoDrivingLicenseResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @PostMapping("/save_info_education")
    public SaveInfoEducationResponse saveInfoEducation(
            @RequestHeader(name = "Authorization") String token, @RequestBody SaveInfoEducationRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.WAREHOUSE_MANAGER);
            ValidateHelper.validateInput(request);
            log.info(requestId + " Request to /saveInfoEducation: " + request);
            log.info(requestId + " User: " + user);
            SaveInfoEducationResponse response = saveInfoEducationService.saveInfoEducation(requestId, request);
            log.info(requestId + " Response from /saveInfoEducation: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveInfoEducationResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @PutMapping("/edit_info_driving_license")
    public EditInfoDrivingLicenseResponse editInfoDrivingLicense(
            @RequestHeader(name = "Authorization") String token, @RequestBody EditInfoDrivingLicenseRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.WAREHOUSE_MANAGER);
            ValidateHelper.validateInput(request);
            log.info(requestId + " Request to /editInfoDrivingLicense: " + request);
            log.info(requestId + " User: " + user);
            EditInfoDrivingLicenseResponse response = editInfoDrivingLicenseService.editInfoDrivingLicense(requestId, user, request);
            log.info(requestId + " Response from /editInfoDrivingLicense: " + response);

            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditInfoDrivingLicenseResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @PutMapping("/edit_info_education")
    public EditInfoEducationResponse editInfoEducation(
            @RequestHeader(name = "Authorization") String token, @RequestBody EditInfoEducationRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.WAREHOUSE_MANAGER);
            ValidateHelper.validateInput(request);
            log.info(requestId + " Request to /editInfoEducation: " + request);
            log.info(requestId + " User: " + user);
            EditInfoEducationResponse response = editInfoEducationService.editInfoEducation(requestId, user, request);
            log.info(requestId + " Response from /editInfoEducation: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditInfoEducationResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @DeleteMapping("/remove_info_driving_license")
    public RemoveInfoDrivingLicenseResponse removeInfoDrivingLicense(
            @RequestHeader(name = "Authorization") String token, @RequestBody RemoveInfoDrivingLicenseRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.WAREHOUSE_MANAGER);
            ValidateHelper.validateInput(request);
            log.info(requestId + " Request to /removeInfoDrivingLicense: " + request);
            log.info(requestId + " User: " + user);
            RemoveInfoDrivingLicenseResponse response = removeInfoDrivingLicenseService.removeInfoDrivingLicense(requestId, user, request);
            log.info(requestId + " Response from /removeInfoDrivingLicense: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new RemoveInfoDrivingLicenseResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @DeleteMapping("/remove_info_education")
    public RemoveInfoEducationResponse removeInfoEducation(
            @RequestHeader(name = "Authorization") String token, @RequestBody RemoveInfoEducationRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.WAREHOUSE_MANAGER);
            ValidateHelper.validateInput(request);
            log.info(requestId + " Request to /removeInfoEducation: " + request);
            log.info(requestId + " User: " + user);
            RemoveInfoEducationResponse response = removeInfoEducationService.removeInfoEducation(requestId, user, request);
            log.info(requestId + " Response from /removeInfoEducation: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new RemoveInfoEducationResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @PostMapping("/save_internship")
    public SaveInternshipResponse saveInternship(
            @RequestHeader(name = "Authorization") String token, @RequestBody SaveInternshipRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.WAREHOUSE_MANAGER);
            ValidateHelper.validateInput(request);
            log.info(requestId + " Request to /saveInternship: " + request);
            log.info(requestId + " User: " + user);
            SaveInternshipResponse response = saveInternshipService.saveInternship(requestId, user, request);
            log.info(requestId + " Response from /saveInternship: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveInternshipResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @DeleteMapping("/remove_internship")
    public SaveInternshipResponse removeInternship(
            @RequestHeader(name = "Authorization") String token, @RequestBody RemoveInternshipRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.WAREHOUSE_MANAGER);
            ValidateHelper.validateInput(request);
            log.info(requestId + " Request to /removeInternship: " + request);
            log.info(requestId + " User: " + user);
            SaveInternshipResponse response = removeInternshipService.removeInternship(requestId, user, request);
            log.info(requestId + " Response from /removeInternship: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveInternshipResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @GetMapping("/get_user_profile")
    public GetUserProfileResponse getUserProfile(
            @RequestHeader(name = "Authorization") String token, @RequestBody GetUserProfileRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.WAREHOUSE_MANAGER);
            ValidateHelper.validateInput(request);
            log.info(requestId + " Request to /getUserProfile: " + request);
            log.info(requestId + " User: " + user);
            GetUserProfileResponse response = getUserProfileService.getUserProfile(requestId, user, request);
            log.info(requestId + " Response from /getUserProfile: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new GetUserProfileResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @PostMapping("/save_info")
    public SaveUserProfileResponse saveUserProfile(
            @RequestHeader(name = "Authorization") String token, @RequestBody SaveUserProfileRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.WAREHOUSE_MANAGER);
            ValidateHelper.validateInput(request);
            log.info(requestId + " Request to /saveUserProfile: " + request);
            log.info(requestId + " User: " + user);
            SaveUserProfileResponse response = saveUserProfileService.saveUserProfile(requestId, request);
            log.info(requestId + " Response from /saveUserProfile: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveUserProfileResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @PostMapping("/exist_document")
    public ExistDocumentResponse existDocument(
            @RequestHeader(name = "Authorization") String token, @RequestBody ExistDocumentRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.WAREHOUSE_MANAGER);
            ValidateHelper.validateInput(request);
            log.info(requestId + " Request to /existDocument: " + request);
            log.info(requestId + " User: " + user);
            ExistDocumentResponse response = saveExistDocumentService.saveExistDocument(requestId, user, request);
            log.info(requestId + " Response from /existDocument: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new ExistDocumentResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @PutMapping("/edit_user_profile")
    public EditUserProfileResponse editUserProfile(
            @RequestHeader(name = "Authorization") String token, @RequestBody EditUserProfileRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.CHIEF_ACCOUNTANT);
            ValidateHelper.validateInput(request);
            log.info(requestId + " Request to /editUserProfile: " + request);
            EditUserProfileResponse response = editUserProfileService.editUserProfile(requestId, user, request);
            log.info(requestId + " Response from /editUserProfile: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditUserProfileResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

}
