package tech.na_app.services.company.edit_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.company.*;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.company.identification_detalis.EditIdentificationDetailsRequest;
import tech.na_app.model.company.identification_detalis.EditIdentificationDetailsResponse;
import tech.na_app.repository.CompanyRepository;

import java.util.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class EditIdentificationDetailsServiceImpl implements EditIdentificationDetailsService {

    private final CompanyRepository companyRepository;

    @Override
    public EditIdentificationDetailsResponse editIdentificationDetails(String requestId, User user, EditIdentificationDetailsRequest request) {
        try {
            Company company = companyRepository.findById(user.getCompanyId())
                    .orElseThrow(() -> new ApiException(404, "Not Found"));

            IdentificationDetails identificationDetails = Objects.requireNonNullElseGet(company.getIdentification_details(), IdentificationDetails::new);
            identificationDetails.setEdrpou(request.getIdentification_details().getEdrpou());
            identificationDetails.setRegistration_certificate(request.getIdentification_details().getRegistration_certificate());
            identificationDetails.setIpn(request.getIdentification_details().getIpn());
            identificationDetails.setAccounting_tax_info(request.getIdentification_details().getAccounting_tax_info());
            identificationDetails.setTax_form(request.getIdentification_details().getTax_form());

            company.setIdentification_details(identificationDetails);

            companyRepository.save(company);

            return new EditIdentificationDetailsResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditIdentificationDetailsResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new EditIdentificationDetailsResponse(new ErrorObject(500, "Something went wrong"));
        }
    }


}
