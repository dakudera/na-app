package tech.na_app.controller.user_profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import tech.na_app.controller.AuthenticationStrategy;
import tech.na_app.controller.BaseController;
import tech.na_app.entity.user.User;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.model.profile.RemoveInternshipRequest;
import tech.na_app.model.profile.SaveInternshipRequest;
import tech.na_app.model.profile.SaveInternshipResponse;
import tech.na_app.services.user_profile.remove_data.RemoveInternshipService;
import tech.na_app.services.user_profile.save_data.SaveInternshipService;
import tech.na_app.utils.LineUtil;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("user_profile/internship")
public class InternshipController extends BaseController {

    private final AuthenticationStrategy authenticationStrategy;
    private final SaveInternshipService saveInternshipService;
    private final RemoveInternshipService removeInternshipService;

    @PostMapping("/save")
    public SaveInternshipResponse saveInternship(
            @RequestHeader(name = "Authorization") String token, @RequestBody SaveInternshipRequest request
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /saveInternship: " + request);
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + LineUtil.USER + user);
            return saveInternshipService.saveInternship(requestId, user, request);
        });
    }

    @DeleteMapping("/internship")
    public SaveInternshipResponse removeInternship(
            @RequestHeader(name = "Authorization") String token, @RequestBody RemoveInternshipRequest request
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /removeInternship: " + request);
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + LineUtil.USER + user);
            return removeInternshipService.removeInternship(requestId, user, request);
        });
    }

}
