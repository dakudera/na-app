package tech.na_app.model.company;

import lombok.Data;
import tech.na_app.entity.company.BankingDetails;
import tech.na_app.entity.company.Communication;
import tech.na_app.entity.company.CompanyName;
import tech.na_app.entity.company.IdentificationDetails;

@Data
public class SaveNewCompanyRequest {

    private Integer id;
    private CompanyName ukr_name;
    private CompanyName eng_name;
    private String address;
    private String postal_address;
    private Communication communication;
    private BankingDetails banking_details;
    private IdentificationDetails identification_details;
    private String licence_info;

}
