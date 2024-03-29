package tech.na_app.model.company;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.na_app.model.company.company_global_info.BankingDetails;
import tech.na_app.model.company.company_global_info.Communication;
import tech.na_app.model.company.conpany_name.CompanyName;
import tech.na_app.model.company.identification_detalis.IdentificationDetails;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SaveNewCompanyRequest {

    @NotNull
    private CompanyName ukr_name;

    @NotNull
    private CompanyName eng_name;

    @Schema(example = "s. Novooleksandrivka, Dnіpropetrovska oblast")
    @NotEmpty
    private String address;

    @Schema(example = "vul. Centralna, bud. 90, s. Novooleksandrivka, Dnіprovskij rajon, Dnіpropetrovska oblast, Ukraina, 52070")
    @NotEmpty
    private String postal_address;

    @NotNull
    private Communication communication;

    @NotNull
    private BankingDetails banking_details;

    @NotNull
    private IdentificationDetails identification_details;

    @NotEmpty
    private String licence_info;

}
