package tech.na_app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.model.profile.*;
import tech.na_app.model.profile.education.*;
import tech.na_app.services.user_profile.UserProfileService;
import tech.na_app.utils.HelpUtil;
import tech.na_app.utils.jwt.AuthChecker;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("user_profile")
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final AuthChecker authChecker;

    @PostMapping("/save_info_education")
    public SaveInfoEducationResponse saveInfoEducation(
            @RequestHeader(name = "Authorization") String token, @RequestBody SaveInfoEducationRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            authChecker.checkToken(token, UserRoleType.CHIEF_ACCOUNTANT);
            log.info(requestId + " Request to /saveInfoEducation: " + request);
            SaveInfoEducationResponse response = userProfileService.saveInfoEducation(requestId, request);
            log.info(requestId + " Response from /saveInfoEducation: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveInfoEducationResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @PostMapping("/edit_info_education")
    public EditInfoEducationResponse editInfoEducation(
            @RequestHeader(name = "Authorization") String token, @RequestBody EditInfoEducationRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.CHIEF_ACCOUNTANT);
            log.info(requestId + " Request to /editInfoEducation: " + request);
            EditInfoEducationResponse response = userProfileService.editInfoEducation(requestId, user, request);
            log.info(requestId + " Response from /editInfoEducation: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditInfoEducationResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @DeleteMapping("/remove_info_education")
    public RemoveInfoEducationResponse removeInfoEducation(
            @RequestHeader(name = "Authorization") String token, @RequestBody RemoveInfoEducationRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.CHIEF_ACCOUNTANT);
            log.info(requestId + " Request to /removeInfoEducation: " + request);
            RemoveInfoEducationResponse response = userProfileService.removeInfoEducation(requestId, user, request);
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
            authChecker.checkToken(token, UserRoleType.CHIEF_ACCOUNTANT);
            log.info(requestId + " Request to /saveInternship: " + request);
            SaveInternshipResponse response = userProfileService.saveInternship(requestId, request);
            log.info(requestId + " Response from /saveInternship: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveInternshipResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @PostMapping("/get_user_profile")
    public GetUserProfileResponse getUserProfile(
            @RequestHeader(name = "Authorization") String token, @RequestBody GetUserProfileRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.CHIEF_ACCOUNTANT);
            log.info(requestId + " Request to /saveInternship: " + request);
            GetUserProfileResponse response = userProfileService.getUserProfile(requestId, user, request);
            log.info(requestId + " Response from /saveInternship: " + response);
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
            authChecker.checkToken(token, UserRoleType.CHIEF_ACCOUNTANT);
            log.info(requestId + " Request to /saveUserProfile: " + request);
            SaveUserProfileResponse response = userProfileService.saveUserProfile(requestId, request);
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
            User user = authChecker.checkToken(token, UserRoleType.CHIEF_ACCOUNTANT);
            log.info(requestId + " Request to /existDocument: " + request);
            ExistDocumentResponse response = userProfileService.saveExistDocument(requestId, user, request);
            log.info(requestId + " Response from /existDocument: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new ExistDocumentResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

}
