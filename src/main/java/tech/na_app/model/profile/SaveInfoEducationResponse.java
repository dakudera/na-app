package tech.na_app.model.profile;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.ErrorObject;

@Data
@NoArgsConstructor
public class SaveInfoEducationResponse {

    private ErrorObject error;

    public SaveInfoEducationResponse(ErrorObject error) {
        this.error = error;
    }

}
