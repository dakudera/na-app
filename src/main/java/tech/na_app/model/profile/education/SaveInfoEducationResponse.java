package tech.na_app.model.profile.education;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.exceptions.ErrorObject;

@Data
@NoArgsConstructor
public class SaveInfoEducationResponse {

    private ErrorObject error;

    public SaveInfoEducationResponse(ErrorObject error) {
        this.error = error;
    }

}
