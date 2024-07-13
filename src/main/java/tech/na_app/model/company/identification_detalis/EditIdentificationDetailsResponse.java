package tech.na_app.model.company.identification_detalis;

import lombok.Data;
import tech.na_app.model.exceptions.ErrorObject;

@Data
public class EditIdentificationDetailsResponse {

    private ErrorObject error;

    public EditIdentificationDetailsResponse(ErrorObject error) {
        this.error = error;
    }
}
