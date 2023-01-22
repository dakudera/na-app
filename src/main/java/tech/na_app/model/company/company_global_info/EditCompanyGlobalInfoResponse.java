package tech.na_app.model.company.company_global_info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.ErrorObject;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditCompanyGlobalInfoResponse {

    private ErrorObject error;

}
