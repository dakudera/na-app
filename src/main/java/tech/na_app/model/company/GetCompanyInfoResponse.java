package tech.na_app.model.company;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.company.company_global_info.BankingDetails;
import tech.na_app.model.company.company_global_info.Communication;
import tech.na_app.model.company.conpany_name.CompanyName;
import tech.na_app.model.company.identification_detalis.IdentificationDetails;
import tech.na_app.model.exceptions.ErrorObject;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCompanyInfoResponse {

    private CompanyName ukrName;
    private CompanyName engName;
    @Schema(example = "s. Novooleksandrivka, Dnipropetrovska oblast")
    private String address;
    @Schema(example = "vul. Centralna, bud. 90, s. Novooleksandrivka, Dnіprovskij rajon, Dnіpropetrovska oblast, Ukraina, 52070")
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
