package tech.na_app.services.company.save_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.converter.CompanyConverter;
import tech.na_app.model.company.SaveNewCompanyRequest;
import tech.na_app.model.company.SaveNewCompanyResponse;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.repository.CompanyRepository;

@Log4j2
@Service
@RequiredArgsConstructor
public class SaveNewCompanyServiceImpl implements SaveNewCompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyConverter companyConverter;

    @Override
    public SaveNewCompanyResponse saveNewCompany(String requestId, SaveNewCompanyRequest request) {
        try {
            companyRepository.save(companyConverter.convertToCompanyEntity(request));
            return new SaveNewCompanyResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveNewCompanyResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new SaveNewCompanyResponse(new ErrorObject(500, "Something went wrong"));
        }
    }
}
