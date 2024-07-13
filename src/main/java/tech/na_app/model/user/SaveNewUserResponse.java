package tech.na_app.model.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.exceptions.ErrorObject;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveNewUserResponse {

    @Schema(
            example = "1"
    )
    private String id;
    private ErrorObject error;

    public SaveNewUserResponse(ErrorObject error) {
        this.error = error;
    }
}
