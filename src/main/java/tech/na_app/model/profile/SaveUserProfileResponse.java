package tech.na_app.model.profile;

import lombok.Data;
import tech.na_app.model.ErrorObject;

@Data
public class SaveUserProfileResponse {

    private ErrorObject error;

    public SaveUserProfileResponse(ErrorObject error) {
        this.error = error;
    }
}
