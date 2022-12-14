package tech.na_app.model.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.ErrorObject;

@Data
@Builder
@NoArgsConstructor
public class SaveInfoEducationResponse {

    private ErrorObject error;

    public SaveInfoEducationResponse(ErrorObject error) {
        this.error = error;
    }

}
