package tech.na_app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.na_app.model.company.SaveNewCompanyRequest;
import tech.na_app.model.company.SaveNewCompanyResponse;
import tech.na_app.services.CompanyService;
import tech.na_app.utils.HelpUtil;

@Log4j2
@RestController
@RequestMapping("company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("save_new")
    public SaveNewCompanyResponse saveNewCompany(@RequestBody SaveNewCompanyRequest request) {
        String requestId = HelpUtil.getUUID();
        log.info(requestId + " Request to saveNewCompany: " + request);
        return companyService.saveNewCompany(requestId, request);
    }

}
