package tech.na_app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.company.GetAllCompanyResponse;
import tech.na_app.model.company.SaveNewCompanyRequest;
import tech.na_app.model.company.SaveNewCompanyResponse;
import tech.na_app.model.enums.UserRole;
import tech.na_app.services.company.CompanyService;
import tech.na_app.utils.HelpUtil;
import tech.na_app.utils.jwt.AuthChecker;

@Log4j2
@RestController
@RequestMapping("company")
@RequiredArgsConstructor
public class CompanyController {

    private final AuthChecker authChecker;
    private final CompanyService companyService;

    @PostMapping("save_new")
    public SaveNewCompanyResponse saveNewCompany(
            @RequestHeader(name = "Authorization") String token, @RequestBody SaveNewCompanyRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            authChecker.checkToken(token, UserRole.SUPER_ADMIN);

            log.info(requestId + " Request to saveNewCompany: " + request);
            return companyService.saveNewCompany(requestId, request);
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveNewCompanyResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @GetMapping("get_all")
    public GetAllCompanyResponse getAllCompany(@RequestHeader(name = "Authorization") String token) {
        String requestId = HelpUtil.getUUID();
        try {
            authChecker.checkToken(token, UserRole.SUPER_ADMIN);
            log.info(requestId + " Request to getAllCompany");
            return companyService.getAllCompanies(requestId, 1);
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new GetAllCompanyResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

}
