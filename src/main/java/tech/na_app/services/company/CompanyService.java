package tech.na_app.services.company;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.converter.CompanyConverter;
import tech.na_app.entity.company.BankingDetails;
import tech.na_app.entity.company.Communication;
import tech.na_app.entity.company.CompanyName;
import tech.na_app.entity.company.*;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.company.*;
import tech.na_app.model.company.company_global_info.EditCompanyGlobalInfoRequest;
import tech.na_app.model.company.company_global_info.EditCompanyGlobalInfoResponse;
import tech.na_app.repository.CompanyRepository;
import tech.na_app.utils.ParameterValidator;
import tech.na_app.utils.SequenceGeneratorService;

import java.util.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final SequenceGeneratorService sequenceGeneratorService;
    private final CompanyConverter companyConverter;

    public SaveNewCompanyResponse saveNewCompany(String requestId, SaveNewCompanyRequest request) {
        try {
            if (request == null) {
                throw new ApiException(400, "BAD_REQUEST");
            }

            CompanySequence sequenceNumber = (CompanySequence) sequenceGeneratorService.getSequenceNumber(User.SEQUENCE_NAME, CompanySequence.class);

            companyRepository.save(companyConverter.convertToCompanyEntity(request, sequenceNumber));

            return new SaveNewCompanyResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveNewCompanyResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new SaveNewCompanyResponse(new ErrorObject(500, "Something went wrong"));
        }
    }


    public GetAllCompanyResponse getAllCompanies(String requestId) {
        try {
            List<Company> all = companyRepository.findAll();
            if (all.isEmpty()) {
                log.info(requestId + " Companies were not found");
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

    public EditCompanyNameResponse editCompanyName(String requestId, User user, EditCompanyNameRequest request) {
        try {
            if (Objects.isNull(request) || Objects.isNull(user.getCompanyId())) {
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

    public EditCompanyGlobalInfoResponse editCompanyGlobalInfo(String requestId, User user, EditCompanyGlobalInfoRequest request) {
        try {
            if (Objects.isNull(request) || Objects.isNull(user.getCompanyId())) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (Objects.isNull(request.getAddress())) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (Objects.isNull(request.getPostal_address())) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (Objects.isNull(request.getBanking_details())) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (Objects.isNull(request.getCommunication())) {
                throw new ApiException(400, "BAD REQUEST");
            }

            if (Objects.nonNull(request.getCommunication().getEmail())) {
                if (!ParameterValidator.emailIsValid(request.getCommunication().getEmail())) {
                    throw new ApiException(500, "Email is invalid");
                }
            }

            Company company = companyRepository.findById(user.getCompanyId())
                    .orElseThrow(() -> new ApiException(404, "Not Found"));

            Communication communication = Objects.requireNonNullElseGet(company.getCommunication(), Communication::new);
            communication.setEmail(request.getCommunication().getEmail());
            communication.setPhone_number(request.getCommunication().getPhone_number());

            BankingDetails bankingDetails = Objects.requireNonNullElseGet(company.getBanking_details(), BankingDetails::new);
            bankingDetails.setIban(request.getBanking_details().getIban());
            bankingDetails.setRemittance_bank(request.getBanking_details().getRemittance_bank());

            company.setAddress(request.getAddress());
            company.setPostal_address(request.getPostal_address());
            company.setBanking_details(bankingDetails);
            company.setCommunication(communication);

            companyRepository.save(company);

            return new EditCompanyGlobalInfoResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditCompanyGlobalInfoResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new EditCompanyGlobalInfoResponse(new ErrorObject(500, "Something went wrong"));
        }
    }


}
