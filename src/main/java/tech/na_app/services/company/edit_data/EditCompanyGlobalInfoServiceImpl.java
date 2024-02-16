package tech.na_app.services.company.edit_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.company.BankingDetails;
import tech.na_app.entity.company.Communication;
import tech.na_app.entity.company.Company;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.company.company_global_info.EditCompanyGlobalInfoRequest;
import tech.na_app.model.company.company_global_info.EditCompanyGlobalInfoResponse;
import tech.na_app.repository.CompanyRepository;

import java.util.Objects;

@Log4j2
@Service
@RequiredArgsConstructor
public class EditCompanyGlobalInfoServiceImpl implements EditCompanyGlobalInfoService {

    private final CompanyRepository companyRepository;

    @Override
    public EditCompanyGlobalInfoResponse editCompanyGlobalInfo(String requestId, User user, EditCompanyGlobalInfoRequest request) {
        try {
            if (Objects.isNull(user.getCompanyId())) {
                throw new ApiException(400, "BAD REQUEST");
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
