package tech.na_app.model.profile.education;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.ErrorObject;

@Data
@NoArgsConstructor
public class EditInfoEducationResponse {

    private ErrorObject error;

    public EditInfoEducationResponse(ErrorObject error) {
        this.error = error;
    }

}
