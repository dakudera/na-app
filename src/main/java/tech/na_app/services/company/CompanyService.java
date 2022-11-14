package tech.na_app.services.company;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.company.*;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.company.GetAllCompanyResponse;
import tech.na_app.model.company.SaveNewCompanyRequest;
import tech.na_app.model.company.SaveNewCompanyResponse;
import tech.na_app.model.enums.UserRole;
import tech.na_app.repository.CompanyRepository;
import tech.na_app.repository.UserRepository;
import tech.na_app.utils.SequenceGeneratorService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    public SaveNewCompanyResponse saveNewCompany(String requestId, SaveNewCompanyRequest request) {
        try {
            if (request == null) {
                throw new ApiException(400, "BAD_REQUEST");
            }

            CompanySequence sequenceNumber = (CompanySequence) sequenceGeneratorService.getSequenceNumber(User.SEQUENCE_NAME, CompanySequence.class);

            companyRepository.save(
                    Company.builder()
                            .id(sequenceNumber.getSeq())
                            .create_date(new Date())
                            .ukr_name(
                                    request.getUkr_name() != null ?
                                            CompanyName.builder()
                                                    .full_name(request.getUkr_name().getFull_name())
                                                    .short_name(request.getUkr_name().getShort_name())
                                                    .build() : null
                            )
                            .eng_name(
                                    request.getEng_name() != null ?
                                            CompanyName.builder()
                                                    .full_name(request.getEng_name().getFull_name())
                                                    .short_name(request.getEng_name().getShort_name())
                                                    .build() : null
                            )
                            .address(request.getAddress())
                            .postal_address(request.getPostal_address())
                            .communication(
                                    request.getCommunication() != null ?
                                            Communication.builder()
                                                    .email(request.getCommunication().getEmail())
                                                    .phone_number(request.getCommunication().getPhone_number())
                                                    .build() : null
                            )
                            .banking_details(
                                    request.getBanking_details() != null ?
                                            BankingDetails.builder()
                                                    .iban(request.getBanking_details().getIban())
                                                    .remittance_bank(request.getBanking_details().getRemittance_bank())
                                                    .build() : null
                            )
                            .identification_details(
                                    request.getIdentification_details() != null ?
                                            IdentificationDetails.builder()
                                                    .edrpou(request.getIdentification_details().getEdrpou())
                                                    .registration_certificate(request.getIdentification_details().getRegistration_certificate())
                                                    .ipn(request.getIdentification_details().getIpn())
                                                    .accounting_tax_info(request.getIdentification_details().getAccounting_tax_info())
                                                    .tax_form(request.getIdentification_details().getTax_form())
                                                    .build() : null
                            )
                            .licence_info(request.getLicence_info())
                            .build()
            );

            return new SaveNewCompanyResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveNewCompanyResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }


    public GetAllCompanyResponse getAllCompanies(String requestId, Integer userId) {
        try {
            if (userId == null) {
                throw new ApiException(400, "BAD_REQUEST");
            }

            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                if (!userOptional.get().getRole().equals(UserRole.SUPER_ADMIN)) {
                    throw new ApiException(400, "BAD_REQUEST");
                }
            }

            List<Company> all = companyRepository.findAll();
            if (all.isEmpty()) {
                return new GetAllCompanyResponse(new ErrorObject(0));
            }
            List<GetAllCompanyResponse.Company> companies = new ArrayList<>();
            for (var company : all) {
                companies.add(
                        GetAllCompanyResponse.Company.builder()
                                .id(company.getId())
                                .name(company.getUkr_name().getFull_name())
                                .registrationDate(company.getCreate_date())
                                .build()
                );
            }

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
