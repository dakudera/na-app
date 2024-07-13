package tech.na_app.model.company.conpany_name;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.exceptions.ErrorObject;

@Data
@NoArgsConstructor
public class EditCompanyNameResponse {

    private ErrorObject error;

    public EditCompanyNameResponse(ErrorObject error) {
        this.error = error;
    }
}
