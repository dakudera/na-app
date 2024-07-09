package tech.na_app.controller.company;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import tech.na_app.entity.user.User;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.company.GetAllCompanyResponse;
import tech.na_app.model.company.GetCompanyInfoResponse;
import tech.na_app.model.company.SaveNewCompanyRequest;
import tech.na_app.model.company.SaveNewCompanyResponse;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.services.company.get_data.GetAllCompaniesService;
import tech.na_app.services.company.get_data.GetCompanyInfoService;
import tech.na_app.services.company.save_data.SaveNewCompanyService;
import tech.na_app.utils.HelpUtil;
import tech.na_app.utils.jwt.AuthChecker;

@Log4j2
@RestController
@RequestMapping("company")
@RequiredArgsConstructor
public class CompanyController {

    private final AuthChecker authChecker;
    private final SaveNewCompanyService saveNewCompanyService;
    private final GetAllCompaniesService getAllCompaniesService;
    private final GetCompanyInfoService getCompanyInfoService;

    @PostMapping("/create-new-company")
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

    @GetMapping("get-all")
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

    @GetMapping("get-company-info")
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



}
