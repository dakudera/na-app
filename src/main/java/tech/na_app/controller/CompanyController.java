package tech.na_app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.company.GetAllCompanyResponse;
import tech.na_app.model.company.GetCompanyInfoResponse;
import tech.na_app.model.company.SaveNewCompanyRequest;
import tech.na_app.model.company.SaveNewCompanyResponse;
import tech.na_app.model.company.company_global_info.EditCompanyGlobalInfoRequest;
import tech.na_app.model.company.company_global_info.EditCompanyGlobalInfoResponse;
import tech.na_app.model.company.conpany_name.EditCompanyNameRequest;
import tech.na_app.model.company.conpany_name.EditCompanyNameResponse;
import tech.na_app.model.company.identification_detalis.EditIdentificationDetailsRequest;
import tech.na_app.model.company.identification_detalis.EditIdentificationDetailsResponse;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.services.company.edit_data.EditCompanyGlobalInfoService;
import tech.na_app.services.company.edit_data.EditCompanyNameService;
import tech.na_app.services.company.edit_data.EditIdentificationDetailsServiceImpl;
import tech.na_app.services.company.get_data.GetAllCompaniesService;
import tech.na_app.services.company.get_data.GetCompanyInfoService;
import tech.na_app.services.company.save_data.SaveNewCompanyService;
import tech.na_app.utils.HelpUtil;
import tech.na_app.utils.ValidateHelper;
import tech.na_app.utils.jwt.AuthChecker;

@Log4j2
@RestController
@RequestMapping("company")
@RequiredArgsConstructor
public class CompanyController {

    private final AuthChecker authChecker;
    private final EditIdentificationDetailsServiceImpl editIdentificationDetailsServiceImpl;
    private final SaveNewCompanyService saveNewCompanyService;
    private final GetAllCompaniesService getAllCompaniesService;
    private final GetCompanyInfoService getCompanyInfoService;
    private final EditCompanyNameService editCompanyNameService;
    private final EditCompanyGlobalInfoService editCompanyGlobalInfoService;

    @PostMapping("save_new")
    public SaveNewCompanyResponse saveNewCompany(
            @RequestHeader(name = "Authorization") String token, @RequestBody SaveNewCompanyRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.SUPER_ADMIN);
//            ValidateHelper.validateInput(request);
            log.info(requestId + " Request to /saveNewCompany: " + request);
            log.info(requestId + " User: " + user);
            SaveNewCompanyResponse response = saveNewCompanyService.saveNewCompany(requestId, request);
            log.info(requestId + " Response from /saveNewCompany: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveNewCompanyResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @GetMapping("get_all")
    public GetAllCompanyResponse getAllCompany(@RequestHeader(name = "Authorization") String token) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + " Request to /getAllCompany");
            log.info(requestId + " User: " + user);
            GetAllCompanyResponse response = getAllCompaniesService.getAllCompanies(requestId);
            log.info(requestId + " Response from /getAllCompany: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new GetAllCompanyResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @GetMapping("get_company_info")
    public GetCompanyInfoResponse getCompanyInfo(@RequestHeader(name = "Authorization") String token) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.WAREHOUSE_MANAGER);
            log.info(requestId + " Request to /getCompanyInfo");
            log.info(requestId + " User: " + user);
            GetCompanyInfoResponse response = getCompanyInfoService.getCompanyInfo(requestId, user);
            log.info(requestId + " Response from /getCompanyInfo: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new GetCompanyInfoResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @PutMapping("edit/company_name")
    public EditCompanyNameResponse editCompanyName(
            @RequestHeader(name = "Authorization") String token, @RequestBody EditCompanyNameRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.WAREHOUSE_MANAGER);
            ValidateHelper.validateInput(request);
            log.info(requestId + " Request to /editCompanyName");
            log.info(requestId + " User: " + user);
            EditCompanyNameResponse response = editCompanyNameService.editCompanyName(requestId, user, request);
            log.info(requestId + " Response from /editCompanyName: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditCompanyNameResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @PutMapping("edit/global_info")
    public EditCompanyGlobalInfoResponse editCompanyGlobalInfo(
            @RequestHeader(name = "Authorization") String token, @RequestBody EditCompanyGlobalInfoRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.WAREHOUSE_MANAGER);
            ValidateHelper.validateInput(request);
            log.info(requestId + " Request to /editCompanyGlobalInfo");
            log.info(requestId + " User: " + user);
            EditCompanyGlobalInfoResponse response = editCompanyGlobalInfoService.editCompanyGlobalInfo(requestId, user, request);
            log.info(requestId + " Response from /editCompanyGlobalInfo: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditCompanyGlobalInfoResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @PutMapping("edit/identification_details")
    public EditIdentificationDetailsResponse editIdentificationDetails(
            @RequestHeader(name = "Authorization") String token, @RequestBody EditIdentificationDetailsRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.WAREHOUSE_MANAGER);
            ValidateHelper.validateInput(request);
            log.info(requestId + " Request to /editIdentificationDetails");
            log.info(requestId + " User: " + user);
            EditIdentificationDetailsResponse response = editIdentificationDetailsServiceImpl.editIdentificationDetails(requestId, user, request);
            log.info(requestId + " Response from /editIdentificationDetails: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditIdentificationDetailsResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

}
