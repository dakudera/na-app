package tech.na_app.model.profile;

import lombok.Data;
import tech.na_app.model.exceptions.ErrorObject;

@Data
public class ExistDocumentResponse {

    private ErrorObject error;

    public ExistDocumentResponse(ErrorObject error) {
        this.error = error;
    }
}
