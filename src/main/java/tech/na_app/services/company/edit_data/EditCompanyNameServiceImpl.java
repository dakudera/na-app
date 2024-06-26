package tech.na_app.services.company.edit_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.company.Company;
import tech.na_app.entity.company.CompanyName;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.company.conpany_name.EditCompanyNameRequest;
import tech.na_app.model.company.conpany_name.EditCompanyNameResponse;
import tech.na_app.repository.CompanyRepository;

import java.util.Objects;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class EditCompanyNameServiceImpl implements EditCompanyNameService {

    private final CompanyRepository companyRepository;


    private CompanyName setCompanyName(CompanyName currentCompanyName, CompanyName newCompanyName) {
        if (newCompanyName != null) {
            if (currentCompanyName != null) {
                currentCompanyName.setFull_name(newCompanyName.getFull_name());
                currentCompanyName.setShort_name(newCompanyName.getShort_name());
            } else {
                return CompanyName
                        .builder()
                        .full_name(newCompanyName.getFull_name())
                        .short_name(newCompanyName.getShort_name())
                        .build();
            }
        }
        return currentCompanyName;
    }


    @Override
    public EditCompanyNameResponse editCompanyName(String requestId, User user, EditCompanyNameRequest request) {
        try {
            if (Objects.isNull(user.getCompanyId())) {
                throw new ApiException(400, "BAD REQUEST");
            }

            Optional<Company> companyOptional = companyRepository.findById(user.getCompanyId());
            Company company = companyOptional.orElseThrow(() -> new ApiException(404, "Not Found"));

            company.setEng_name(setCompanyName(company.getEng_name(), request.getEng_name()));
            company.setUkr_name(setCompanyName(company.getUkr_name(), request.getUkr_name()));
            companyRepository.save(company);

            return new EditCompanyNameResponse(new ErrorObject(0));
        } catch (Exception e) {
            log.error(requestId + " Error: " + 500 + " Message: " + e.getMessage());
            return new EditCompanyNameResponse(new ErrorObject(500, e.getMessage()));
        }
    }
}
