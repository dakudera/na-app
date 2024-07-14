package tech.na_app.controller.user_profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import tech.na_app.controller.AuthenticationStrategy;
import tech.na_app.controller.BaseController;
import tech.na_app.entity.user.User;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.model.profile.*;
import tech.na_app.services.user_profile.edit_data.EditUserProfileService;
import tech.na_app.services.user_profile.get_data.GetUserProfileService;
import tech.na_app.services.user_profile.save_data.SaveExistDocumentService;
import tech.na_app.services.user_profile.save_data.SaveUserProfileService;
import tech.na_app.utils.LineUtil;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("user_profile")
public class GeneralInfoController extends BaseController {

    private final AuthenticationStrategy authenticationStrategy;
    private final SaveUserProfileService saveUserProfileService;
    private final EditUserProfileService editUserProfileService;
    private final GetUserProfileService getUserProfileService;
    private final SaveExistDocumentService saveExistDocumentService;

    @GetMapping("/get")
    public GetUserProfileResponse getUserProfile(
            @RequestHeader(name = "Authorization") String token, @RequestBody GetUserProfileRequest request
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /getUserProfile: " + request);
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + LineUtil.USER + user);
            return getUserProfileService.getUserProfile(requestId, user, request);
        });
    }

    @PostMapping("/exist_document")
    public ExistDocumentResponse existDocument(
            @RequestHeader(name = "Authorization") String token, @RequestBody ExistDocumentRequest request
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /existDocument: " + request);
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + LineUtil.USER + user);
            return saveExistDocumentService.saveExistDocument(requestId, user, request);
        });
    }

    @PostMapping("/create_new")
    public SaveUserProfileResponse saveUserProfile(
            @RequestHeader(name = "Authorization") String token, @RequestBody SaveUserProfileRequest request
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /saveUserProfile: " + request);
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + LineUtil.USER + user);
            return saveUserProfileService.saveUserProfile(requestId, request);
        });
    }

    @PutMapping("/update")
    public EditUserProfileResponse editUserProfile(
            @RequestHeader(name = "Authorization") String token, @RequestBody EditUserProfileRequest request
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /editUserProfile: " + request);
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + LineUtil.USER + user);
            return editUserProfileService.editUserProfile(requestId, user, request);
        });
    }

}
