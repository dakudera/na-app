package tech.na_app.model.profile;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.ErrorObject;

@Data
@Builder
@NoArgsConstructor
public class EditUserProfileResponse {

    private ErrorObject error;

    public EditUserProfileResponse(ErrorObject error) {
        this.error = error;
    }

}
