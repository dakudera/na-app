package tech.na_app.model.company;

import lombok.Data;
import tech.na_app.model.ErrorObject;

@Data
public class SaveNewCompanyResponse {

    private ErrorObject error;

    public SaveNewCompanyResponse(ErrorObject error) {
        this.error = error;
    }
}
