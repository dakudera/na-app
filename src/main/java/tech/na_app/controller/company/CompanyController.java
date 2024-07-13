package tech.na_app.controller.company;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import tech.na_app.controller.AuthenticationStrategy;
import tech.na_app.controller.BaseController;
import tech.na_app.entity.user.User;
import tech.na_app.model.company.GetAllCompanyResponse;
import tech.na_app.model.company.GetCompanyInfoResponse;
import tech.na_app.model.company.SaveNewCompanyRequest;
import tech.na_app.model.company.SaveNewCompanyResponse;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.services.company.get_data.GetAllCompaniesService;
import tech.na_app.services.company.get_data.GetCompanyInfoService;
import tech.na_app.services.company.save_data.SaveNewCompanyService;

@Log4j2
@RestController
@RequestMapping("company")
@RequiredArgsConstructor
public class CompanyController extends BaseController {

    private final AuthenticationStrategy authenticationStrategy;
    private final SaveNewCompanyService saveNewCompanyService;
    private final GetAllCompaniesService getAllCompaniesService;
    private final GetCompanyInfoService getCompanyInfoService;

    @PostMapping("/create-new-company")
    public SaveNewCompanyResponse saveNewCompany(
            @RequestHeader(name = "Authorization") String token, @RequestBody SaveNewCompanyRequest request
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /saveNewCompany: " + request);
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + " User: " + user);
            return saveNewCompanyService.saveNewCompany(requestId, request);
        });
    }

    @GetMapping("get-all")
    public GetAllCompanyResponse getAllCompany(@RequestHeader(name = "Authorization") String token) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /getAllCompany");
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + " User: " + user);
            return getAllCompaniesService.getAllCompanies(requestId);
        });
    }

    @GetMapping("get-company-info")
    public GetCompanyInfoResponse getCompanyInfo(@RequestHeader(name = "Authorization") String token) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /getCompanyInfo");
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.WAREHOUSE_MANAGER);
            log.info(requestId + " User: " + user);
            return getCompanyInfoService.getCompanyInfo(requestId, user);
        });
    }

}
