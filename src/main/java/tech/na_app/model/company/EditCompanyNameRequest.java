package tech.na_app.model.company;

import lombok.Data;
import tech.na_app.entity.company.CompanyName;

@Data
public class EditCompanyNameRequest {

    private CompanyName ukr_name;
    private CompanyName eng_name;


}
