package tech.na_app.controller.company;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import tech.na_app.controller.AuthenticationStrategy;
import tech.na_app.controller.BaseController;
import tech.na_app.entity.user.User;
import tech.na_app.model.company.company_global_info.EditCompanyGlobalInfoRequest;
import tech.na_app.model.company.company_global_info.EditCompanyGlobalInfoResponse;
import tech.na_app.model.company.conpany_name.EditCompanyNameRequest;
import tech.na_app.model.company.conpany_name.EditCompanyNameResponse;
import tech.na_app.model.company.identification_detalis.EditIdentificationDetailsRequest;
import tech.na_app.model.company.identification_detalis.EditIdentificationDetailsResponse;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.services.company.edit_data.EditCompanyGlobalInfoService;
import tech.na_app.services.company.edit_data.EditCompanyNameService;
import tech.na_app.services.company.edit_data.EditIdentificationDetailsService;
import tech.na_app.utils.LineUtil;
import tech.na_app.utils.ValidateHelper;

@Log4j2
@RestController
@RequestMapping("company")
@RequiredArgsConstructor
public class EditCompanyInfoController extends BaseController {

    private final AuthenticationStrategy authenticationStrategy;
    private final EditCompanyNameService editCompanyNameService;
    private final EditCompanyGlobalInfoService editCompanyGlobalInfoService;
    private final EditIdentificationDetailsService editIdentificationDetailsService;

    @PutMapping("edit/company-name")
    public EditCompanyNameResponse editCompanyName(
            @RequestHeader(name = "Authorization") String token, @RequestBody EditCompanyNameRequest request
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /editCompanyName");
        return handleRequest(requestId, () -> {
            ValidateHelper.validateInput(request);
            User user = authenticationStrategy.authenticate(token, UserRoleType.WAREHOUSE_MANAGER);
            log.info(requestId + LineUtil.USER + user);
            return editCompanyNameService.editCompanyName(requestId, user, request);
        });
    }

    @PutMapping("edit/global-info")
    public EditCompanyGlobalInfoResponse editCompanyGlobalInfo(
            @RequestHeader(name = "Authorization") String token, @RequestBody EditCompanyGlobalInfoRequest request
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /editCompanyGlobalInfo");
        return handleRequest(requestId, () -> {
            ValidateHelper.validateInput(request);
            User user = authenticationStrategy.authenticate(token, UserRoleType.WAREHOUSE_MANAGER);
            log.info(requestId + LineUtil.USER + user);
            return editCompanyGlobalInfoService.editCompanyGlobalInfo(requestId, user, request);
        });
    }

    @PutMapping("edit/identification-details")
    public EditIdentificationDetailsResponse editIdentificationDetails(
            @RequestHeader(name = "Authorization") String token, @RequestBody EditIdentificationDetailsRequest request
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /editIdentificationDetails");
        return handleRequest(requestId, () -> {
            ValidateHelper.validateInput(request);
            User user = authenticationStrategy.authenticate(token, UserRoleType.WAREHOUSE_MANAGER);
            log.info(requestId + LineUtil.USER + user);
            return editIdentificationDetailsService.editIdentificationDetails(requestId, user, request);
        });
    }

}
