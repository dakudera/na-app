package tech.na_app.controller.user_profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import tech.na_app.controller.AuthenticationStrategy;
import tech.na_app.controller.BaseController;
import tech.na_app.entity.user.User;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.model.profile.education.*;
import tech.na_app.services.user_profile.edit_data.EditInfoEducationService;
import tech.na_app.services.user_profile.remove_data.RemoveInfoEducationService;
import tech.na_app.services.user_profile.save_data.SaveInfoEducationService;
import tech.na_app.utils.ValidateHelper;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("user_profile/education")
public class EducationInfoController extends BaseController {

    private final AuthenticationStrategy authenticationStrategy;
    private final SaveInfoEducationService saveInfoEducationService;
    private final EditInfoEducationService editInfoEducationService;
    private final RemoveInfoEducationService removeInfoEducationService;

    @PostMapping("/save")
    public SaveInfoEducationResponse saveInfoEducation(
            @RequestHeader(name = "Authorization") String token, @RequestBody SaveInfoEducationRequest request
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /saveInfoEducation: " + request);
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            ValidateHelper.validateInput(request);
            log.info(requestId + " User: " + user);
            return saveInfoEducationService.saveInfoEducation(requestId, request);
        });
    }

    @PutMapping("/update")
    public EditInfoEducationResponse editInfoEducation(
            @RequestHeader(name = "Authorization") String token, @RequestBody EditInfoEducationRequest request
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /editInfoEducation: " + request);
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + " User: " + user);
            return editInfoEducationService.editInfoEducation(requestId, user, request);
        });
    }

    @DeleteMapping("/remove")
    public RemoveInfoEducationResponse removeInfoEducation(
            @RequestHeader(name = "Authorization") String token, @RequestBody RemoveInfoEducationRequest request
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /removeInfoEducation: " + request);
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + " User: " + user);
            return removeInfoEducationService.removeInfoEducation(requestId, user, request);
        });
    }

}
