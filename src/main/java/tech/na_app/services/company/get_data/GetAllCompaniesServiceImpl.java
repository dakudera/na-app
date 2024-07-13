package tech.na_app.services.company.get_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.company.Company;
import tech.na_app.model.company.GetAllCompanyResponse;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.repository.CompanyRepository;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class GetAllCompaniesServiceImpl implements GetAllCompaniesService {

    private final CompanyRepository companyRepository;

    @Override
    public GetAllCompanyResponse getAllCompanies(String requestId) {
        try {
            List<Company> all = companyRepository.findAll();
            if (all.isEmpty()) {
                log.info(requestId + " Companies were not found");
                return new GetAllCompanyResponse(new ErrorObject(0));
            }
            List<GetAllCompanyResponse.Company> companies = new ArrayList<>();
            all.forEach(
                    a -> companies.add(
                            GetAllCompanyResponse.Company.builder()
                                    .id(a.getId())
                                    .name(a.getUkr_name().getFull_name())
                                    .registrationDate(a.getCreate_date())
                                    .build()
                    )
            );

            return GetAllCompanyResponse.builder()
                    .companies(companies)
                    .errorObject(new ErrorObject(0))
                    .build();
        } catch (Exception e) {
            log.error(requestId + " Error: " + 500 + " Message: " + e.getMessage());
            return new GetAllCompanyResponse(new ErrorObject(500, e.getMessage()));
        }
    }

}
