package tech.na_app.model.company;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.na_app.model.company.company_global_info.BankingDetails;
import tech.na_app.model.company.company_global_info.Communication;
import tech.na_app.model.company.conpany_name.CompanyName;
import tech.na_app.model.company.identification_detalis.IdentificationDetails;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record SaveNewCompanyRequest(
        @NotNull
        CompanyName ukr_name,
        @NotNull
        CompanyName eng_name,
        @Schema(example = "s. Novooleksandrivka, Dnіpropetrovska oblast")
        @NotEmpty
        String address,
        @Schema(example = "vul. Centralna, bud. 90, s. Novooleksandrivka, Dnіprovskij rajon, Dnіpropetrovska oblast, Ukraina, 52070")
        @NotEmpty
        String postal_address,
        @NotNull
        Communication communication,
        @NotNull
        BankingDetails banking_details,
        @NotNull
        IdentificationDetails identification_details,
        @NotEmpty
        String licence_info
) {

}
