package tech.na_app.model.company.company_global_info;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.exceptions.ErrorObject;

@Data
@NoArgsConstructor
public class EditCompanyGlobalInfoResponse {

    private ErrorObject error;

    public EditCompanyGlobalInfoResponse(ErrorObject error) {
        this.error = error;
    }
}
