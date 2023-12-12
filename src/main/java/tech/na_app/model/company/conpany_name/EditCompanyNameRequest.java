package tech.na_app.model.company.conpany_name;

import lombok.Data;
import tech.na_app.entity.company.CompanyName;

import javax.validation.constraints.NotNull;

@Data
public class EditCompanyNameRequest {

    @NotNull
    private CompanyName ukr_name;

    @NotNull
    private CompanyName eng_name;


}
