package tech.na_app.model.company;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.na_app.model.company.company_global_info.BankingDetails;
import tech.na_app.model.company.company_global_info.Communication;
import tech.na_app.model.company.conpany_name.CompanyName;
import tech.na_app.model.company.identification_detalis.IdentificationDetails;

@Data
public class SaveNewCompanyRequest {

    private Integer id;
    private CompanyName ukr_name;
    private CompanyName eng_name;
    @Schema(example = "s. Novooleksandrivka, Dnіpropetrovska oblast")
    private String address;
    @Schema(example = "vul. Centralna, bud. 90, s. Novooleksandrivka, Dnіprovskij rajon, Dnіpropetrovska oblast, Ukraina, 52070")
    private String postal_address;
    private Communication communication;
    private BankingDetails banking_details;
    private IdentificationDetails identification_details;
    private String licence_info;

}
