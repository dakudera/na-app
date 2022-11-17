package tech.na_app.model.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.ErrorObject;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCompanyInfoResponse {

    private CompanyName ukrName;
    private CompanyName engName;
    private String address;
    private String postalAddress;
    private Communication communication;
    private BankingDetails bankingDetails;
    private IdentificationDetails identificationDetails;
    private String licenceInfo;

    private ErrorObject error;

    public GetCompanyInfoResponse(ErrorObject error) {
        this.error = error;
    }

}
