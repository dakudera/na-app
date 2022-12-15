package tech.na_app.model.profile.education;

import lombok.Data;
import tech.na_app.model.ErrorObject;

@Data
public class RemoveInfoEducationResponse {

    private ErrorObject error;

    public RemoveInfoEducationResponse(ErrorObject error) {
        this.error = error;
    }

}
