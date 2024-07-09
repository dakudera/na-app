package tech.na_app.services.company.get_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.converter.CompanyConverter;
import tech.na_app.entity.company.Company;
import tech.na_app.entity.user.User;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.company.GetCompanyInfoResponse;
import tech.na_app.repository.CompanyRepository;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class GetCompanyInfoServiceImpl implements GetCompanyInfoService {

    private final CompanyRepository companyRepository;
    private final CompanyConverter companyConverter;

    @Override
    public GetCompanyInfoResponse getCompanyInfo(String requestId, User user) {
        try {
            Optional<Company> companyOptional = companyRepository.findById(user.getCompanyId());
            if (companyOptional.isEmpty()) {
                log.info(requestId + " Company was not found");
                return new GetCompanyInfoResponse(new ErrorObject(0));
            }

            return companyConverter.convertToGetCompanyInfoResponse(companyOptional.get());
        } catch (Exception e) {
            log.error(requestId + " Error: " + 500 + " Message: " + e.getMessage());
            return new GetCompanyInfoResponse(new ErrorObject(500, e.getMessage()));
        }
    }
}
