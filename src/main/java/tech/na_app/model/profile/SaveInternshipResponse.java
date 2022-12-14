package tech.na_app.model.profile;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.ErrorObject;

@Data
@NoArgsConstructor
public class SaveInternshipResponse {

    private ErrorObject error;

    public SaveInternshipResponse(ErrorObject error) {
        this.error = error;
    }

}
