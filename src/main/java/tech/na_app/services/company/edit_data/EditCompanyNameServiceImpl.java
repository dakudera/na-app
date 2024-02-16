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

    @Override
    public EditCompanyNameResponse editCompanyName(String requestId, User user, EditCompanyNameRequest request) {
        try {
            if (Objects.isNull(user.getCompanyId())) {
                throw new ApiException(400, "BAD REQUEST");
            }

            Optional<Company> companyOptional = companyRepository.findById(user.getCompanyId());
            Company company = companyOptional.orElseThrow(() -> new ApiException(404, "Not Found"));

            if (request.getEng_name() != null) {
                if (company.getEng_name() != null) {
                    company.getEng_name().setFull_name(request.getEng_name().getFull_name());
                    company.getEng_name().setShort_name(request.getEng_name().getShort_name());
                } else {
                    CompanyName companyNameEng = CompanyName
                            .builder()
                            .full_name(request.getEng_name().getFull_name())
                            .short_name(request.getEng_name().getShort_name())
                            .build();
                    company.setEng_name(companyNameEng);
                }
            }

            if (request.getUkr_name() != null) {
                if (company.getUkr_name() != null) {
                    company.getUkr_name().setFull_name(request.getUkr_name().getFull_name());
                    company.getUkr_name().setShort_name(request.getUkr_name().getShort_name());
                } else {
                    CompanyName companyNameUkr = CompanyName
                            .builder()
                            .full_name(request.getUkr_name().getFull_name())
                            .short_name(request.getUkr_name().getShort_name())
                            .build();
                    company.setUkr_name(companyNameUkr);
                }
            }

            companyRepository.save(company);

            return new EditCompanyNameResponse(new ErrorObject(0));
        } catch (Exception e) {
            log.error(requestId + " Error: " + 500 + " Message: " + e.getMessage());
            return new EditCompanyNameResponse(new ErrorObject(500, e.getMessage()));
        }
    }
}
